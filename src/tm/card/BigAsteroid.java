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

// Decrease any 4 plants is done from dummy player
public class BigAsteroid extends Card {

    public BigAsteroid() {
        super("Big Asteroid", 27, new Tags().space().event(), false);
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
            null,
            "Big Asteroid",
            new IncomeDeltaAction(new Resources(0, 0, 4, 0, 0, 0)),
            new AddTemperatureAction(),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("4 titanium", "2 temperature");
    }
}
