package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTemperatureAction;

// Decrease any 3 plants is done from dummy player
public class Asteroid extends Card {

    public Asteroid() {
        super("Asteroid", 14, Tags.SPACE_EVENT);
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.TITANIUM_2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddTemperatureAction();
    }
}
