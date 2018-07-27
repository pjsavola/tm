package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;
import tm.requirement.TemperatureRequirement;

public class TundraFarming extends Card {

    public TundraFarming() {
        super("Tundra Farming", 16, Tags.PLANT, new TemperatureRequirement(-6, true));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT),
            new IncomeDeltaAction(new Resources(2, 0, 0, 1, 0, 0))
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -6C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant", "2 money income", "1 plant income");
    }
}
