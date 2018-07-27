package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;

// Money removed from dummy player
public class Flooding extends Card {

    public Flooding() {
        super("Flooding", 7, Tags.EVENT);
    }

    @Override
    public int getVPs() {
        return -1;
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
