package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;

public class SubterraneanReservoir extends Card {

    public SubterraneanReservoir() {
        super("Subterranean Reservoir", 11, Tags.EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddWaterAction();
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 ocean");
    }
}
