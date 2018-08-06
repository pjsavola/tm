package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

// Remove 2 heat income is done from dummy player
public class HeatTrappers extends Card {

    public HeatTrappers() {
        super("Heat Trappers", 6, Tags.BUILDING_POWER);
    }

    @Override
    public int getVPs() {
        return -1;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY;
    }
}
