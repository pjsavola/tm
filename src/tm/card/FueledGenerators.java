package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class FueledGenerators extends Card {

    public FueledGenerators() {
        super("Fueled Generators", 1, Tags.BUILDING.combine(Tags.POWER));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(-1, 0, 0, 0, 1, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-1 money income", "1 energy income");
    }
}
