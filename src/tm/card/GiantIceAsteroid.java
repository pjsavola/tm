package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.AddWaterAction;

// Decrease any 6 plants is done from dummy player
public class GiantIceAsteroid extends Card {

    public GiantIceAsteroid() {
        super("Giant Ice Asteroid", 36, Tags.SPACE.combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddTemperatureAction(),
            new AddTemperatureAction(),
            new AddWaterAction(),
            new AddWaterAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 temperature", "2 oceans");
    }
}
