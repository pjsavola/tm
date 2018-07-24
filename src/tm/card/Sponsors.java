package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Sponsors extends Card {

    public Sponsors() {
        super("Sponsors", 6, Tags.EARTH);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(2));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 money income");
    }
}
