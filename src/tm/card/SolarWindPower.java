package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class SolarWindPower extends Card {

    public SolarWindPower() {
        super("Solar Wind Power", 11, Tags.SCIENCE.combine(Tags.SPACE).combine(Tags.POWER));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.TITANIUM_2),
            new IncomeDeltaAction(Resources.ENERGY)
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 titanium", "1 energy income");
    }
}
