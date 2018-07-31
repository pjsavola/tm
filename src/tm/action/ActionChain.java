package tm.action;


import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.ActionType;
import tm.Game;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class ActionChain implements Action {

    @Nullable
    private final ActionType type;
    private final String description;
    private final Action[] actions;

    public ActionChain(Action... actions) {
        this(null, "", actions);
    }

    public ActionChain(ActionType type, String description, Action... actions) {
        this.type = type;
        this.description = description;
        this.actions = actions;
    }

    @Override
    public ActionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean check(Game game) {
        for (Action action : actions) {
            if (!action.check(game)) {
                if (!action.isOptional()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Completable begin(Game game) {
        if (actions.length == 1) {
            return actions[0].begin(game);
        }
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                for (Action action : actions) {
                    if (action.check(game)) {
                        game.getActionHandler().addPendingAction(action);
                    }
                }
            }

            @Override
            public void undo() {
            }

            @Override
            public void redo() {
            }
        };
    }

    @Override
    public boolean isUndoable() {
        for (Action action : actions) {
            if (!action.isUndoable()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        int currentX = x;
        for (Action action : actions) {
            action.render(g, currentX, y);
            currentX += 30;
        }
    }
}
