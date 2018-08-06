package tm;

import tm.action.Action;

public abstract class StandardAction {
    private final String name;
    private final ActionType type;

    public StandardAction(String name, ActionType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ActionType getType() {
        return type;
    }

    public boolean check(Game game) {
        return true;
    }

    public abstract Action getInitialAction(Game game);
}
