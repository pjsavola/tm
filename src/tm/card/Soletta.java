package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Soletta extends Card {

    public Soletta() {
        super("Soletta", 35, Tags.SPACE);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, 0, 7);
    }
}
