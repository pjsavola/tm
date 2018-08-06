package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class GHGFactories extends Card {

    public GHGFactories() {
        super("GHG Factories", 11, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, -1, 4);
    }
}
