package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Mine extends Card {

    public Mine() {
        super("Mine", 4, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.STEEL);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 steel income");
    }
}
