package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class Thorgate extends Corporation {

    public Thorgate() {
        super("Thorgate", new Tags().power());
    }

    @Override
    protected Action getInitialAction() {
        return new ActionChain(
        	new ResourceDeltaAction(new Resources(48)),
        	new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0)));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("48 money", "1 energy income", "Everything with power tag costs 3 less");
    }
}
