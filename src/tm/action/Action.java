package tm.action;

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
}
