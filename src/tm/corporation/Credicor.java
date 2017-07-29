package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

// TODO: If a card costs 20+, get 4 money
public class Credicor extends Corporation {

    public Credicor() {
        super(new Tags());
    }

    @Override
    protected Action getInitialAction() {
       	return new ResourceDeltaAction(new Resources(57));
    }

    @Override
    public String getTitle() {
        return "Credicor";
    }
}
