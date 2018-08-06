package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

// Remove 1 energy income is done from dummy opponent
public class EnergyTapping extends Card {

    public EnergyTapping() {
        super("Energy Tapping", 3, Tags.POWER);
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
