package tm.card;

import java.util.Arrays;
import java.util.List;

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
    public Resources getIncomeDelta(Game game) {
        return Resources.ENERGY;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddTemperatureAction();
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 enery income", "1 temperature");
    }
}
