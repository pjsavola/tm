package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Greenhouses extends Card {

    public Greenhouses() {
        super("Greenhouses", 6, Tags.PLANT.combine(Tags.BUILDING));
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(0, 0, 0, game.getCityCount(false), 0, 0);
    }
}
