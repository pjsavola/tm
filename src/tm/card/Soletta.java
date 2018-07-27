package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Soletta extends Card {

    public Soletta() {
        super("Soletta", 15, Tags.SPACE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 0, 7));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("7 titanium income");
    }
}
