package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class GreatDam extends Card {

    public GreatDam() {
        super("Great Dam", 12, Tags.BUILDING_POWER, new OceanRequirement(4, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 2, 0);
    }
}
