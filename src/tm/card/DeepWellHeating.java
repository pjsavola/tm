package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.IncomeDeltaAction;

public class DeepWellHeating extends Card {

    public DeepWellHeating() {
        super("Deep Well Heating", 13, new Tags().building().power(), false);
    }

    @Override
    public boolean check(Player player) {
        return player.getIncome().titanium > 0;
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(null, "",
            new IncomeDeltaAction(new Resources(0, 0, 0, 0, 1, 0)),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 enery income", "1 temperature");
    }
}
