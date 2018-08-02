package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class PeroxidePower extends Card {

    public PeroxidePower() {
        super("Peroxide Power", 7, Tags.BUILDING_POWER);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(-1, 0, 0, 0, 2, 0);
    }
}
