package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;
import tm.requirement.OceanRequirement;

public class NitrophilicMoss extends Card {

    public NitrophilicMoss() {
        super("Moss", 8, Tags.PLANT, new OceanRequirement(3, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT_2.negate()),
            new IncomeDeltaAction(Resources.PLANT_2)
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-2 plants", "2 plant income");
    }
}
