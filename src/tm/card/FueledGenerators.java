package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class FueledGenerators extends Card {

    public FueledGenerators() {
        super("Fueled Generators", 1, Tags.BUILDING_POWER);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(-1, 0, 0, 0, 1, 0);
    }
}
