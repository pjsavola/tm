package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TagRequirement;

// Remove 1 energy income is done from dummy opponent
public class PowerSupplyConsortium extends Card {

    public PowerSupplyConsortium() {
        super("Power Supply Consotrium", 5, Tags.POWER, new TagRequirement(Tags.Type.POWER.createTags(2)));
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY;
    }
}
