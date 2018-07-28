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

// Decrease any 8 plants is done from dummy player
public class DeimosDown extends Card {

    public DeimosDown() {
        super("Deimos Down", 31, Tags.SPACE_EVENT);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(0, 4, 0, 0, 0, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddTemperatureAction(),
            new AddTemperatureAction(),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("3 temperature");
    }
}
