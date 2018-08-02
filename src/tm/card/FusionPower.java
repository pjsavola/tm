package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TagRequirement;

public class FusionPower extends Card {

    public FusionPower() {
        super("Fusion Power", 14, Tags.SCIENCE.combine(Tags.BUILDING_POWER), new TagRequirement(Tags.Type.POWER.createTags(2)));
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 3, 0);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2 power tags");
    }
}
