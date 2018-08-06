package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Lichen extends Card {

    public Lichen() {
        super("Lichen", 7, Tags.PLANT, new TemperatureRequirement(-24, true));
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT;
    }
}
