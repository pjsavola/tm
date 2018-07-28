package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;

// Decrease any 4 plants is done from dummy player
public class BigAsteroid extends Card {

    public BigAsteroid() {
        super("Big Asteroid", 27, Tags.SPACE_EVENT);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(0, 0, 4, 0, 0, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(new AddTemperatureAction(), new AddTemperatureAction());
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 temperature");
    }
}
