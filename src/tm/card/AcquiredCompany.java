package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class AcquiredCompany extends Card {

    public AcquiredCompany() {
        super("Acquired Company", 10, Tags.EARTH);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(3);
    }
}
