package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Bushes extends Card {

    public Bushes() {
        super("Bushes", 10, Tags.PLANT, new TemperatureRequirement(-10, true));
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_2;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT_2;
    }
}
