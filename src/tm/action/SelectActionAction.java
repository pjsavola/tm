package tm.action;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.completable.Completable;

public class SelectActionAction implements Action {

    @Override
    public boolean check(Game game) {
        return game.getCurrentPlayer().getPlayedCards().stream().filter(card -> !card.getActions().isEmpty()).count() > 0;
    }

    @Override
    public Completable begin(Game game) {
        final List<Action> selectableActions = new ArrayList<>();
        game.getCurrentPlayer().getPlayedCards().forEach(card -> selectableActions.addAll(card.getActions()));
        return new SelectActionCompletable(game, selectableActions);
    }

    private static class SelectActionCompletable implements Completable {
        private final Game game;
        private final List<Action> selectableActions;
        protected static final int TOP_MARGIN = 80;
        protected static final int LEFT_MARGIN = 100;
        protected static final int CARD_SPACING = 4;
        protected static final int CARD_HEIGHT = Card.TITLE_HEIGHT + CARD_SPACING;
        protected static final int VISIBLE_SPACING = 100;
        private final Color TITLE_COLOR = new Color(0xFFFFFF);
        private final Color HIGHLIGHT_COLOR = new Color(0xFFFF00);
        private Action selectedAction;

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
                for (int i = 0; i < selectableActions.size(); i++) {
                    if (x >= LEFT_MARGIN && x <= LEFT_MARGIN + Card.WIDTH && y >= TOP_MARGIN + CARD_HEIGHT * (i + 1) && y <= TOP_MARGIN + CARD_HEIGHT * (i + 2)) {
                        if (selectedAction == selectableActions.get(i)) {
                            selectedAction = null;
                        } else {
                            selectedAction = selectableActions.get(i);
                        }
                        game.repaint();
                        break;
                    }
                }
                if (x >= LEFT_MARGIN && x <= LEFT_MARGIN + Card.WIDTH && y >= TOP_MARGIN + CARD_HEIGHT * (selectableActions.size() + 2) && y <= TOP_MARGIN + CARD_HEIGHT * (selectableActions.size() + 3)) {
                    if (selectedAction != null && selectedAction.check(game)) {
                        game.getActionHandler().completed(SelectActionCompletable.this);
                    }
                }
            }
        };

        public SelectActionCompletable(Game game, List<Action> selectableActions) {
            this.game = game;
            this.selectableActions = selectableActions;
            game.addMouseListener(mouseListener);
            game.repaint();
        }

        @Override
        public void complete() {
            game.getActionHandler().addPendingAction(selectedAction);
            cancel();
        }

        @Override
        public void undo() {
        }

        @Override
        public void redo() {
        }

        @Override
        public void cancel() {
            game.removeMouseListener(mouseListener);
            game.repaint();
        }

        @Override
        public void paint(Graphics g) {
            final Color oldColor = g.getColor();
            g.setColor(TITLE_COLOR);
            final String title = "Select action";
            g.drawString(title, LEFT_MARGIN, TOP_MARGIN);

            for (int i = 0; i < selectableActions.size(); i++) {
                if (selectedAction == selectableActions.get(i)) {
                    g.setColor(HIGHLIGHT_COLOR);
                    g.drawRect(LEFT_MARGIN - 1, TOP_MARGIN + CARD_HEIGHT * (i + 1) - 1, Card.WIDTH + 2, Card.TITLE_HEIGHT + 2);
                }
                final boolean selectable = selectableActions.get(i).check(game);
                String name = selectableActions.get(i).getType().toString();
                if (!selectable) {
                    name = "(" + name + ")";
                }
                drawAction(g, name, LEFT_MARGIN, TOP_MARGIN + CARD_HEIGHT * (i + 1));
            }
            g.setColor(TITLE_COLOR);
            g.drawString("Confirm", LEFT_MARGIN, TOP_MARGIN + CARD_HEIGHT * (selectableActions.size() + 2) + 12);
            g.setColor(oldColor);
        }

        private static void drawAction(Graphics g, String name, int x, int y) {
            g.setFont(Card.FONT);
            final FontMetrics metrics = g.getFontMetrics();
            final int h = metrics.getHeight();
            final int w = metrics.stringWidth(name);

            // Draw background
            g.setColor(Color.BLACK);
            g.fillRect(x, y, Card.WIDTH, Card.TITLE_HEIGHT);

            // Draw border
            g.setColor(Color.GRAY);
            g.drawRect(x, y, Card.WIDTH, Card.TITLE_HEIGHT);

            // Draw text
            g.setColor(Color.WHITE);
            g.drawString(name, x + (Card.WIDTH - w) / 2 - 8, y + 14);
        }
    }
}
