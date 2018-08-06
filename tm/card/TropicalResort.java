package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class TropicalResort extends Card {

    public TropicalResort() {
        super("Tropical Resort", 13, Tags.BUILDING);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(3, 0, 0, 0, 0, -2);
    }
}
