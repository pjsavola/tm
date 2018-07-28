package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
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
    public Resources getIncomeDelta(Game game) {
        return Resources.ENERGY;
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 energy income");
    }
}
