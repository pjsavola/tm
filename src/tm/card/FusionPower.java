package tm.card;

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
}
