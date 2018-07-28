package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Satellites extends Card {

    public Satellites() {
        super("Satellites", 10, Tags.SPACE);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.SPACE) + 1);
    }
}
