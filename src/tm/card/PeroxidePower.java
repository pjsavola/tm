package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class PeroxidePower extends Card {

    public PeroxidePower() {
        super("Peroxide Power", 7, Tags.BUILDING_POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(-1, 0, 0, 0, 2, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-1 money income", "2 energy income");
    }
}
