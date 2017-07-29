package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

// TODO: 7 plants instead of 8 for a greenery
public class Ecoline extends Corporation {

    public Ecoline() {
        super(new Tags().plant());
    }

    @Override
    protected Action getInitialAction() {
       	return new ActionChain(
       		new ResourceDeltaAction(new Resources(36, 0, 0, 3, 0, 0)),
       		new IncomeDeltaAction(new Resources(0, 0, 0, 2, 0, 0)));
    }

    @Override
    public String getTitle() {
        return "Ecoline";
    }
}
