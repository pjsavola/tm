package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

// TODO: -3 cost for energy symbols
public class Thorgate extends Corporation {

    public Thorgate() {
        super(new Tags().energy());
    }

    @Override
    protected Action getInitialAction() {
        return new ActionChain(
        	new ResourceDeltaAction(new Resources(48)),
        	new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0)));
    }

    @Override
    public String getTitle() {
        return "Thorgate";
    }
}
