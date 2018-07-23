package tm.corporation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTerraformingRatingAction;
import tm.action.ResourceDeltaAction;

public class UnitedNationsMarsInitiative extends Corporation {

    public UnitedNationsMarsInitiative() {
        super("United Nations Mars Initiative", Tags.PLANT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources(40));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(ActionType.TR, "Increase TR",
            new ResourceDeltaAction(new Resources(-3)),
            new AddTerraformingRatingAction() {
                @Override
                public boolean check(Game game) {
                    return game.getCurrentPlayer().hasIncreasedRating();
                }
            }
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("40 money", "May increase TR for 3 money if TR has been increased this turn");
    }
}
