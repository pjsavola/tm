package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.AddWaterAction;

// Decrease any 3 plants is done from dummy player
public class Comet extends Card {

    public Comet() {
        super("Comet", 21, new Tags().space().event(), false);
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
            null,
            "Comet",
            new AddWaterAction(),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 water", "1 temperature");
    }
}