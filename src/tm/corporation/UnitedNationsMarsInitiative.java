package tm.corporation;

import java.util.Arrays;
import java.util.List;

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

    @Override
    protected List<String> getContents() {
        return Arrays.asList("40 money", "May increase TR for 3 money if TR has been increased this turn");
    }
}
