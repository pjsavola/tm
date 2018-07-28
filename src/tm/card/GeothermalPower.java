package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class GeothermalPower extends Card {

    public GeothermalPower() {
        super("Geothermal Power", 11, Tags.BUILDING_POWER);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, 2, 0);
    }
}
