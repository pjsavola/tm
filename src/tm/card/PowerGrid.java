package tm.card;

import java.util.Collections;
import java.util.List;

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

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 energy income for each power tag");
    }
}
