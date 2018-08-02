package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class AcquiredCompany extends Card {

    public AcquiredCompany() {
        super("Acquired Company", 10, Tags.EARTH);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(3);
    }
}
