package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

// TODO: Using heat as money
public class Helion extends Corporation {

    public Helion() {
        super(new Tags().space());
    }

    @Override
    protected Action getInitialAction() {
        return new ActionChain(
        	new ResourceDeltaAction(new Resources(42)),
        	new IncomeDeltaAction(new Resources(0, 0, 0, 0, 0, 3)));
    }

    @Override
    public String getTitle() {
        return "Helion";
    }
}
