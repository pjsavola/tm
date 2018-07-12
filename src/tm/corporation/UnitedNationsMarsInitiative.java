package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class UnitedNationsMarsInitiative extends Corporation {

    public UnitedNationsMarsInitiative() {
        super("United Nations Mars Initiative", new Tags().plant());
    }

    @Override
    protected Action getInitialAction() {
       	return new ResourceDeltaAction(new Resources(40));
    }
}
