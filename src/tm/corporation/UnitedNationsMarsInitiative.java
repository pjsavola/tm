package tm.corporation;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

// TODO: action for increasing rating with 3 money if you raised rating before this round
public class UnitedNationsMarsInitiative extends Corporation {

    public UnitedNationsMarsInitiative() {
        super(new Tags().plant());
    }

    @Override
    protected Action getInitialAction() {
       	return new ResourceDeltaAction(new Resources(40));
    }

    @Override
    public String getTitle() {
        return "United Nations Mars Initiative";
    }
}
