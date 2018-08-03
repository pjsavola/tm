package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Farming extends Card {

    public Farming() {
        super("Farming", 16, Tags.PLANT, new TemperatureRequirement(4, true));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_2;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2, 0, 0, 2, 0, 0);
    }
}
