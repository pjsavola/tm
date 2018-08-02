package tm.action;

import java.awt.Graphics;
import java.awt.Point;

import tm.ActionType;
import tm.Game;
import tm.completable.Completable;

public interface Action {

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

    /**
     * Returns bottom-right corner of the bounding box needed for this rendering.
     */
    default Point render(Graphics g, int x, int y, Game game) {
        return new Point(0, 0);
    }
}
