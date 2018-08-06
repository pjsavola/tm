package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class GeothermalPower extends Card {

    public GeothermalPower() {
        super("Geothermal Power", 11, Tags.BUILDING_POWER);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 2, 0);
    }
}
