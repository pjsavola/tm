package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class CarbonateProcessing extends Card {

    public CarbonateProcessing() {
        super("Carbonate Processing", 6, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, -1, 3);
    }
}
