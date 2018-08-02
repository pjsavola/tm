package tm.action;


import java.awt.Graphics;
import java.awt.Point;

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
    public Point render(Graphics g, int x, int y, Game game) {
        int currentX = x;
        int maxY = y;
        for (int i = 0; i < actions.length; i++) {
            if (i != 0) {
                currentX += 4; // spacing
            }
            final Point p = actions[i].render(g, currentX, y, game);
            currentX += p.x;
            if (p.y > maxY) {
                maxY = p.y;
            }
        }
        return new Point(currentX, maxY);
    }
}
