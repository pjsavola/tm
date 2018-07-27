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

public class Heather extends Card {

    public Heather() {
        super("Heather", 6, Tags.PLANT, new TemperatureRequirement(-14, true));
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
        return Collections.singletonList("Requires -14C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant", "1 plant income");
    }
}
