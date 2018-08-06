package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class Mine extends Card {

    public Mine() {
        super("Mine", 4, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.STEEL;
    }
}
