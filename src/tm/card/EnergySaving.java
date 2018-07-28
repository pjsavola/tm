package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class EnergySaving extends Card {

    public EnergySaving() {
        super("Energy Saving", 15, Tags.POWER);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, game.getCityCount(false), 0);
    }
}
