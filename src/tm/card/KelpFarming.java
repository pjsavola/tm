package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class KelpFarming extends Card {

    public KelpFarming() {
        super("Kelp Farming", 17, Tags.EMPTY, new OceanRequirement(6, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_2;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2, 0, 0, 3, 0, 0);
    }
}
