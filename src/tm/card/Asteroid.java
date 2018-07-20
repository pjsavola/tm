package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.IncomeDeltaAction;

// Decrease any 3 plants is done from dummy player
public class Asteroid extends Card {

    public Asteroid() {
        super("Asteroid", 14, new Tags().space().event(), false);
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
            null,
            "Asteroid",
            new IncomeDeltaAction(new Resources(0, 0, 2, 0, 0, 0)),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 titanium", "1 temperature");
    }
}
