package tm.corporation;

import java.util.Arrays;
import java.util.List;

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
    public Action getInitialAction() {
        return new ResourceDeltaAction(new Resources(23, 0, 10, 0, 0, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("23 money", "10 titanium", "Titanium value +1");
    }
}
