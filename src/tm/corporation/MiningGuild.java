package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

// TODO: When placing on top of titanium or steel, gain 1 steel prod
public class MiningGuild extends Corporation {

    public MiningGuild() {
        super("Mining Guild", new Tags().building().building());
    }

    @Override
    protected Action getInitialAction() {
        return new ActionChain(
        	new ResourceDeltaAction(new Resources(30, 5, 0, 0, 0, 0)),
        	new IncomeDeltaAction(new Resources(0, 1, 0, 0, 0, 0)));
    }
}
