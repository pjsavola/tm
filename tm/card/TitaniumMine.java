package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class TitaniumMine extends Card {

    public TitaniumMine() {
        super("Titanium Mine", 7, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
    }
}
