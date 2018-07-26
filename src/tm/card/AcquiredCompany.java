package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class AcquiredCompany extends Card {

    public AcquiredCompany() {
        super("Acquired Company", 10, Tags.EARTH);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(3));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("3 money income");
    }
}
