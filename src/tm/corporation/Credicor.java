package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class Credicor extends Corporation {

    public Credicor() {
        super("Credicor", new Tags());
    }

    @Override
    protected Action getInitialAction() {
       	return new ResourceDeltaAction(new Resources(57));
    }
}
