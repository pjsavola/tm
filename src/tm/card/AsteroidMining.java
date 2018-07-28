package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class AsteroidMining extends Card {

    public AsteroidMining() {
        super("Asteroid Mining", 30, Tags.SPACE_JOVIAN);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.TITANIUM_2;
    }
}
