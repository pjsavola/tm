package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class SolarPower extends Card {

    public SolarPower() {
        super("Solar Power", 11, Tags.BUILDING_POWER);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.ENERGY;
    }
}
