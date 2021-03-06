package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class NuclearPower extends Card {

    public NuclearPower() {
        super("Nuclear Power", 10, Tags.BUILDING_POWER);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(-2, 0, 0, 0, 3, 0);
    }
}
