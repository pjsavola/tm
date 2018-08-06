package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class FuelFactory extends Card {

    public FuelFactory() {
        super("Fuel Factory", 6, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(1, 0, 1, 0, -1, 0);
    }
}
