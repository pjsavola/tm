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

public class Helion extends Corporation {

    public Helion() {
        super("Helion", new Tags().space());
    }

    @Override
    protected Action getInitialAction() {
        return new ActionChain(
        	new ResourceDeltaAction(new Resources(42)),
        	new IncomeDeltaAction(new Resources(0, 0, 0, 0, 0, 3)));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("42 money", "3 heat income", "May convert heat to money");
    }
}
