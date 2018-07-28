package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class SoilFactory extends Card {

    public SoilFactory() {
        super("Soil Factory", 9, Tags.BUILDING);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 1, -1, 0);
    }
}
