package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;

public class StripMine extends Card {

    public StripMine() {
        super("Strip Mine", 25, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 2, 1, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(new AddOxygenAction(), new AddOxygenAction());
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 oxygen");
    }
}
