package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;
import tm.requirement.TagRequirement;

public class TectonicStressPower extends Card {

    public TectonicStressPower() {
        super("Tectonic Stress Power", 18, Tags.BUILDING_POWER, new TagRequirement(Tags.SCIENCE_2));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, 3, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddTerraformingRatingAction();
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 TR");
    }
}
