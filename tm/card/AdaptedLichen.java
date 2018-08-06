package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class AdaptedLichen extends Card {

    public AdaptedLichen() {
        super("Adapted Lichen", 9, Tags.PLANT);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT;
    }
}
