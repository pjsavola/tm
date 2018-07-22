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

// Decrease any 8 plants is done from dummy player
public class DeimosDown extends Card {

    public DeimosDown() {
        super("Deimos Down", 31, new Tags().space().event());
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(0, 4, 0, 0, 0, 0)),
            new AddTemperatureAction(),
            new AddTemperatureAction(),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("4 steel", "3 temperature");
    }
}
