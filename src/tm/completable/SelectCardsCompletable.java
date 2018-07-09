package tm.completable;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tm.Card;
import tm.Game;

public abstract class SelectCardsCompletable implements Completable {

    protected static final int TOP_MARGIN = 80;
    protected static final int LEFT_MARGIN = 100;
    protected static final int CARD_SPACING = 4;
    protected static final int CARD_HEIGHT = Card.TITLE_HEIGHT + CARD_SPACING;
    protected static final int VISIBLE_SPACING = 100;
    private static final Color TITLE_COLOR = new Color(0xFFFFFF);
    private static final Color HIGHLIGHT_COLOR = new Color(0xFFFF00);
    private final Game game;
    protected final Set<Card> selectedCards = new HashSet<>();
    protected final List<Card> selection;
    Card cardToRender;

    protected final MouseListener mouseListener = new MouseListener() {
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
                    final int max = maxSelection();
                    if (max > 0) {
                        if (max == 1 && !selectedCards.isEmpty() && !selectedCards.contains(cardToRender)) {
                            selectedCards.clear();
                        }
                        if (!selectedCards.add(cardToRender)) {
                            selectedCards.remove(cardToRender);
                            cardToRender = null;
                        }
                    }
                    selectionChanged();
                    game.repaint();
                    break;
                }
            }
            if (x >= LEFT_MARGIN && x <= LEFT_MARGIN + Card.WIDTH && y >= TOP_MARGIN + CARD_HEIGHT * (selection.size() + 2) && y <= TOP_MARGIN + CARD_HEIGHT * (selection.size() + 3)) {
                if (check()) {
                    game.getActionHandler().completed(SelectCardsCompletable.this);
                }
            }
        }
    };

    protected SelectCardsCompletable(Game game, List<Card> selection) {
        this.game = game;
        this.selection = selection;
        game.addMouseListener(mouseListener);
        game.repaint();
    }

    protected void selectionChanged() {
    }

    public int maxSelection() {
        return Integer.MAX_VALUE;
    }

    public abstract boolean check();

    @Override
    public void cancel() {
        game.removeMouseListener(mouseListener);
        game.repaint();
    }

    public abstract String getTitle();

    @Override
    public void paint(Graphics g) {
        final Color oldColor = g.getColor();
        g.setColor(TITLE_COLOR);
        final String title = getTitle();
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
