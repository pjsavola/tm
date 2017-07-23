package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

// TODO: +1 value for titanium
public class Phoblog extends Corporation {

    public Phoblog() {
        super(new Tags().space());
    }

    @Override
    protected Action getInitialAction() {
        return new ResourceDeltaAction(new Resources(23, 0, 10, 0, 0, 0));
    }

    @Override
    public String getTitle() {
        return "Phoblog";
    }
}
