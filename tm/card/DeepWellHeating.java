package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTemperatureAction;

public class DeepWellHeating extends Card {

    public DeepWellHeating() {
        super("Deep Well Heating", 13, Tags.BUILDING_POWER);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddTemperatureAction();
    }
}
