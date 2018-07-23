package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.ResourceDeltaAction;

// Decrease any 3 plants is done from dummy player
public class Asteroid extends Card {

    public Asteroid() {
        super("Asteroid", 14, new Tags().space().event());
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.TITANIUM_2),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 titanium", "1 temperature");
    }
}
