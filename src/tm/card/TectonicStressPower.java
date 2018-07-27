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
import tm.action.AddTerraformingRatingAction;
import tm.action.IncomeDeltaAction;
import tm.requirement.TagRequirement;

public class TectonicStressPower extends Card {

    public TectonicStressPower() {
        super("Tectonic Stress Power", 18, Tags.BUILDING.combine(Tags.POWER), new TagRequirement(Tags.Type.SCIENCE.createTags(2)));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(new Resources(0, 0, 0, 0, 3, 0)),
            new AddTerraformingRatingAction()
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 energy income", "1 TR");
    }
}
