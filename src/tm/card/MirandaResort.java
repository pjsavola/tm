package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class MirandaResort extends Card {

    public MirandaResort() {
        super("Miranda Resort", 12, Tags.SPACE_JOVIAN);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.EARTH));
    }
}
