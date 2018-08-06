package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class Sponsors extends Card {

    public Sponsors() {
        super("Sponsors", 6, Tags.EARTH);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2);
    }
}
