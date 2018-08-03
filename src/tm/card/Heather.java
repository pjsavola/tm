package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Heather extends Card {

    public Heather() {
        super("Heather", 6, Tags.PLANT, new TemperatureRequirement(-14, true));
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT;
    }
}
