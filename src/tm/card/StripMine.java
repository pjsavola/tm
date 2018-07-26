package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.IncomeDeltaAction;

public class StripMine extends Card {

    public StripMine() {
        super("Strip Mine", 4, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(new Resources(0, 2, 1, 0, -1, 0)),
            new AddOxygenAction(),
            new AddOxygenAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 steel income", "1 titanium income", "-1 power income", "2 oxygen");
    }
}
