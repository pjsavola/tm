package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class Phoblog extends Corporation {

    public Phoblog() {
        super("Phoblog", new Tags().space());
    }

    @Override
    protected Action getInitialAction() {
        return new ResourceDeltaAction(new Resources(23, 0, 10, 0, 0, 0));
    }
}
