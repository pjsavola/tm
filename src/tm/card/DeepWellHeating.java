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
import tm.action.IncomeDeltaAction;

public class DeepWellHeating extends Card {

    public DeepWellHeating() {
        super("Deep Well Heating", 13, Tags.BUILDING.combine(Tags.POWER));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(Resources.ENERGY),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 enery income", "1 temperature");
    }
}
