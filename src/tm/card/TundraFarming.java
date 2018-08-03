package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class TundraFarming extends Card {

    public TundraFarming() {
        super("Tundra Farming", 16, Tags.PLANT, new TemperatureRequirement(-6, true));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2, 0, 0, 1, 0, 0);
    }
}
