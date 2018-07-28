package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class MineralDeposit extends Card {

    public MineralDeposit() {
        super("Mineral Deposit", 5, Tags.EVENT);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(0, 5, 0, 0, 0, 0);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("5 steel");
    }
}
