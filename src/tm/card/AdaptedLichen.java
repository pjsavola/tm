package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class AdaptedLichen extends Card {

    public AdaptedLichen() {
        super("Adapted Lichen", 9, Tags.PLANT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.PLANT);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 plant income");
    }
}
