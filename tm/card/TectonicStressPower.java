package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
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
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 3, 0);
    }
}
