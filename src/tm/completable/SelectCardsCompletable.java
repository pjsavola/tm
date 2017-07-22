package tm.completable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class SelectCardsCompletable implements Completable {

    public enum Type { PLAY, DRAW, DISCARD, DRAW_AND_KEEP };

    private static final int TOP_MARGIN = 80;
    private static final int LEFT_MARGIN = 100;
    private static final int CARD_SPACING = 4;
    private static final int CARD_HEIGHT = Card.TITLE_HEIGHT + CARD_SPACING;
    private static final int VISIBLE_SPACING = 100;
    private static final Color TITLE_COLOR = new Color(0xFFFFFF);
    private static final Color HIGHLIGHT_COLOR = new Color(0xFFFF00);
    private final Game game;
    final Set<Card> selectedCards = new HashSet<>();
    final List<Card> selection;
    final List<Card> hand;
    Card cardToRender;
    private final Type type;

    final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseReleased(MouseEvent arg0) {
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            final int x = e.getX();
            final int y = e.getY();
            for (int i = 0; i < selection.size(); i++) {
                if (x >= LEFT_MARGIN && x <= LEFT_MARGIN + Card.WIDTH && y >= TOP_MARGIN + CARD_HEIGHT * (i + 1) && y <= TOP_MARGIN + CARD_HEIGHT * (i + 2)) {
                    cardToRender = selection.get(i);
                    if (type != Type.DRAW_AND_KEEP) {
                        if (type == Type.PLAY && !selectedCards.isEmpty() && !selectedCards.contains(cardToRender)) {
                            selectedCards.clear();
                        }
                        if (!selectedCards.add(cardToRender)) {
                            selectedCards.remove(cardToRender);
                            cardToRender = null;
                        }
                    }
                    game.repaint();
                    break;
                }
            }
            if (x >= LEFT_MARGIN && x <= LEFT_MARGIN + Card.WIDTH && y >= TOP_MARGIN + CARD_HEIGHT * (selection.size() + 2) && y <= TOP_MARGIN + CARD_HEIGHT * (selection.size() + 3)) {
                switch (type) {
                case PLAY:
                    if (selectedCards.isEmpty()) {
                        System.err.println ("You must select a card to play it");
                        return;
                    }
                    final int cost = selectedCards.iterator().next().getCost();
                    final Action paymentAction = new ResourceDeltaAction(new Resources(-cost));
                    if (paymentAction.check(game)) {
                        game.getActionHandler().addPendingAction(paymentAction);
                    } else {
                        System.err.println("Not enough moeny to pay for the card");
                        return;
                    }
                    break;
                case DRAW:
                    final Action action = new ResourceDeltaAction(new Resources(-3 * selectedCards.size()));
                    if (action.check(game)) {
                        game.getActionHandler().addPendingAction(action);
                    } else {
                        System.err.println("Not enough money to keep the cards");
                    }
                    break;
                case DISCARD:
                    if (selectedCards.isEmpty()) {
                        System.err.println ("You must discard at least one card");
                        return;
                    }
                    game.getActionHandler().addPendingAction(new ResourceDeltaAction(new Resources(selectedCards.size())));
                    break;
                case DRAW_AND_KEEP:
                    break;
                }
                game.getActionHandler().completed(SelectCardsCompletable.this);
            }
        }
    };

    public SelectCardsCompletable(final Game game, final List<Card> selection, final Type type) {
        this.game = game;
        this.selection = selection;
        hand = type == Type.DRAW ? null : new ArrayList<>(game.getCurrentPlayer().getCards());
        this.type = type;
        game.addMouseListener(mouseListener);
        game.repaint();
    }

    @Override
    public void cancel() {
        game.removeMouseListener(mouseListener);
        game.repaint();
    }

    @Override
    public void complete() {
        if (type == Type.DRAW) {
            game.getCurrentPlayer().getCards().addAll(selectedCards);
        } else if (type == Type.DRAW_AND_KEEP) {
            game.getCurrentPlayer().getCards().addAll(selection);
        } else {
            game.getCurrentPlayer().getCards().removeAll(selectedCards);
        }
        cancel();
    }

    @Override
    public void undo() {
        if (type == Type.DRAW) {
            game.getCurrentPlayer().getCards().removeAll(selectedCards);
            game.getActionHandler().reprocess(this);
        } else if (type == Type.DRAW_AND_KEEP) {
            game.getCurrentPlayer().getCards().removeAll(selection);
            game.getActionHandler().reprocess(this);
        } else {
            game.getCurrentPlayer().getCards().clear();
            game.getCurrentPlayer().getCards().addAll(hand);
        }
        game.addMouseListener(mouseListener);
        game.repaint();
    }

    @Override
    public void redo() {
        if (type == Type.DRAW) {
            game.getCurrentPlayer().getCards().addAll(selectedCards);
        } else if (type == Type.DRAW_AND_KEEP) {
            game.getCurrentPlayer().getCards().addAll(selection);
        } else {
            game.getCurrentPlayer().getCards().removeAll(selectedCards);
        }
        cancel();
    }

    @Override
    public void paint(Graphics g) {
        final Color oldColor = g.getColor();
        g.setColor(TITLE_COLOR);
        final String title;
        switch (type) {
        case PLAY:
            title = "Select card to play";
            break;
        case DISCARD:
            title = "Select cards to discard";
            break;
        case DRAW:
            title = "Select cards to keep";
            break;
        case DRAW_AND_KEEP:
            title = "You got these new cards";
            break;
        default:
            title = "";
            break;
        }
        g.drawString(title, LEFT_MARGIN, TOP_MARGIN);
        for (int i = 0; i < selection.size(); i++) {
            if (selectedCards.contains(selection.get(i))) {
                g.setColor(HIGHLIGHT_COLOR);
                g.drawRect(LEFT_MARGIN - 1, TOP_MARGIN + CARD_HEIGHT * (i + 1) - 1, Card.WIDTH + 2, Card.TITLE_HEIGHT + 2);
            }
            selection.get(i).renderTitle(g, LEFT_MARGIN, TOP_MARGIN + CARD_HEIGHT * (i + 1));
        }
        g.setColor(TITLE_COLOR);
        g.drawString("Confirm", LEFT_MARGIN, TOP_MARGIN + CARD_HEIGHT * (selection.size() + 2) + 12);
        if (cardToRender != null) {
            cardToRender.renderTitle(g, LEFT_MARGIN + Card.WIDTH + VISIBLE_SPACING, TOP_MARGIN + CARD_HEIGHT);
        }
        g.setColor(oldColor);
    }
}
