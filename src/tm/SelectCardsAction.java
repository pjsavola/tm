package tm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class SelectCardsAction implements Action {

    final Game game;
    final List<Card> selection;
    final Set<Card> selectedCards = new HashSet<>();
    Card cardToRender;

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
                    if (!selectedCards.add(cardToRender)) {
                        selectedCards.remove(cardToRender);
                        cardToRender = null;
                    }
                    game.repaint();
                    break;
                }
            }
        }
    };

    public SelectCardsAction(final List<Card> selection, final Game game) {
        this.game = game;
        this.selection = selection;
    }

    @Override
    public char getKey() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean check() {
        return !selection.isEmpty();
    }

    @Override
    public void begin() {
        game.addMouseListener(mouseListener);
    }

    @Override
    public void cancel() {
        game.removeMouseListener(mouseListener);
    }

    @Override
    public void complete() {
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    @Override
    public void paint(Graphics g) {
        final Color oldColor = g.getColor();
        g.setColor(TITLE_COLOR);
        g.drawString("Card selection", LEFT_MARGIN, TOP_MARGIN);
        for (int i = 0; i < selection.size(); i++) {
            if (selectedCards.contains(selection.get(i))) {
                g.setColor(HIGHLIGHT_COLOR);
                g.drawRect(LEFT_MARGIN - 1, TOP_MARGIN + CARD_HEIGHT * (i + 1) - 1, Card.WIDTH + 2, Card.TITLE_HEIGHT + 2);
            }
            selection.get(i).renderTitle(g, LEFT_MARGIN, TOP_MARGIN + CARD_HEIGHT * (i + 1));
        }
        if (cardToRender != null) {
            cardToRender.renderTitle(g, LEFT_MARGIN + Card.WIDTH + VISIBLE_SPACING, TOP_MARGIN + CARD_HEIGHT);
        }
        g.setColor(oldColor);
    }

    private static final int TOP_MARGIN = 80;
    private static final int LEFT_MARGIN = 100;
    private static final int CARD_SPACING = 4;
    private static final int CARD_HEIGHT = Card.TITLE_HEIGHT + CARD_SPACING;
    private static final int VISIBLE_SPACING = 100;
    private static final Color TITLE_COLOR = new Color(0xFFFFFF);
    private static final Color HIGHLIGHT_COLOR = new Color(0xFFFF00);
}
