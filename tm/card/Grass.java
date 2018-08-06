package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Grass extends Card {

    public Grass() {
        super("Grass", 11, Tags.PLANT, new TemperatureRequirement(-16, true));
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT_3;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT;
    }
}
