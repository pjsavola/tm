package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.OceanRequirement;

public class WavePower extends Card {

    public WavePower() {
        super("Wave Power", 8, Tags.POWER, new OceanRequirement(3, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY;
    }
}
