package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class MineralDeposit extends Card {

    public MineralDeposit() {
        super("Mineral Deposit", 5, Tags.EVENT);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(0, 5, 0, 0, 0, 0);
    }
}
