package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

// TODO: +1 income per jovian tag
public class SaturnSystems extends Corporation {

    public SaturnSystems() {
        super("Saturn Systems", new Tags().jovian());
    }

    @Override
    protected Action getInitialAction() {
        return new ActionChain(
        	new ResourceDeltaAction(new Resources(42)),
        	new IncomeDeltaAction(new Resources(1, 0, 1, 0, 0, 0)));
    }
}
