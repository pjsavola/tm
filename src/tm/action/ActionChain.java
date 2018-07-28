package tm.action;


import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.ActionType;
import tm.Game;
import tm.completable.Completable;
import tm.completable.CompletableChain;

// TODO: ActionChain inside ActionChain probably does not work. Work around it by using pending actions.
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
        final List<Completable> completables = new ArrayList<>();
        for (Action action : actions) {
            if (!action.isOptional() || action.check(game)) {
                completables.add(action.begin(game));
            }
        }
        return new CompletableChain(game, completables.toArray(new Completable[completables.size()]));
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
}
