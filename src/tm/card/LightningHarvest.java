package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TagRequirement;

public class LightningHarvest extends Card {

    public LightningHarvest() {
        super("LightningHarvest", 8, Tags.POWER, new TagRequirement(Tags.SCIENCE_3));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(1, 0, 0, 0, 1, 0);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 science tags");
    }
}
