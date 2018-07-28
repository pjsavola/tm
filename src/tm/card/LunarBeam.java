package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class LunarBeam extends Card {

    public LunarBeam() {
        super("Lunar Beam", 13, Tags.EARTH.combine(Tags.POWER));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(-2, 0, 0, 0, 2, 2);
    }
}
