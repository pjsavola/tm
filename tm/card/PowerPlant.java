package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class PowerPlant extends Card {

    public PowerPlant() {
        super("Power Plant", 4, Tags.BUILDING_POWER);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY;
    }
}
