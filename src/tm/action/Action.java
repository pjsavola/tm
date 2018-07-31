package tm.action;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Selectable;
import tm.completable.Completable;

public interface Action extends Selectable {

    default ActionType getType() {
        throw new UnsupportedOperationException();
    }

    default String getDescription() {
        return getType().toString();
    }

    default boolean check(Game game) {
        return true;
    }

    Completable begin(Game game);

    default boolean isOptional() {
        return false;
    }

    default boolean isUndoable() {
        return true;
    }

    default Resources getResourceDelta(Game game) {
        return Resources.EMPTY;
    }

    default Resources getIncomeDelta(Game game) {
        return Resources.EMPTY;
    }

    @Override
    default void renderTitle(Graphics g, int x, int y) {
        final FontMetrics metrics = g.getFontMetrics();
        final int h = metrics.getHeight();
        final int w = metrics.stringWidth(getDescription());

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(x, y, Card.WIDTH, Card.TITLE_HEIGHT);

        // Draw border
        g.setColor(Color.GRAY);
        g.drawRect(x, y, Card.WIDTH, Card.TITLE_HEIGHT);

        // Draw text
        g.setColor(Color.WHITE);
        g.drawString(getDescription(), x + (Card.WIDTH - w) / 2 - 8, y + 14);

        g.setFont(Card.FONT);
        g.drawString(getDescription(), x, y);
    }

    @Override
    default void renderContent(Graphics g, int x, int y, Game game) {
        // Draw background
        g.setColor(Card.BG_COLOR);
        g.fillRect(x, y, Card.WIDTH, Card.CARD_HEIGHT);

        // Draw border
        g.setColor(Color.WHITE);
        g.drawRect(x, y, Card.WIDTH, Card.CARD_HEIGHT);

        // Draw resources and income
        int offset = getResourceDelta(game).render(g, x, y + Card.TITLE_HEIGHT + 20, false);
        getIncomeDelta(game).render(g, x, y + Card.TITLE_HEIGHT + 20 + offset, true);

        // Draw content
    }
}
