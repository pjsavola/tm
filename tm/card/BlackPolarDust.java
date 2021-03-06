package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;

public class BlackPolarDust extends Card {

    public BlackPolarDust() {
        super("Black Polar Dust", 15, Tags.EMPTY);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(-2, 0, 0, 0, 0, 3);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddWaterAction();
    }
}
