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

public class Trees extends Card {

    public Trees() {
        super("Trees", 13, Tags.PLANT, new TemperatureRequirement(-4, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT),
            new IncomeDeltaAction(Resources.PLANT)
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -4C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant", "1 plant income");
    }
}
