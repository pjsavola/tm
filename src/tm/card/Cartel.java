package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Cartel extends Card {

    public Cartel() {
        super("Cartel", 8, Tags.EARTH);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.EARTH) + 1);
    }
}
