package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class Teractor extends Corporation {

    public Teractor() {
        super("Teractor", new Tags().earth());
    }

    @Override
    protected Action getInitialAction() {
       	return new ResourceDeltaAction(new Resources(60));
    }
}
