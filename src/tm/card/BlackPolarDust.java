package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddWaterAction;
import tm.action.IncomeDeltaAction;

public class BlackPolarDust extends Card {

    public BlackPolarDust() {
        super("Black Polar Dust", 15, Tags.EMPTY);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(new Resources(-2, 0, 0, 0, 0, 3)),
            new AddWaterAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-2 money income", "3 heat income", "1 water");
    }
}
