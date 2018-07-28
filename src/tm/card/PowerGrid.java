package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class PowerGrid extends Card {

    public PowerGrid() {
        super("Power Grid", 18, Tags.POWER);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, game.getCurrentPlayer().getTags().getCount(Tags.Type.POWER) + 1, 0);
    }
}
