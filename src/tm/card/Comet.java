package tm.card;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.AddWaterAction;

// Decrease any 3 plants is done from dummy player
public class Comet extends Card {

    public Comet() {
        super("Comet", 21, Tags.SPACE_EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(new AddWaterAction(), new AddTemperatureAction());
    }
}
