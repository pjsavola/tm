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
        super("Mine", 4, new Tags().building());
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 1, 0, 0, 0, 0));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 steel income");
    }
}
