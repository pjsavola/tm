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

public class Grass extends Card {

    public Grass() {
        super("Grass", 11, Tags.PLANT, new TemperatureRequirement(-16, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT_3),
            new IncomeDeltaAction(Resources.PLANT)
        );

    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -16C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 plants", "1 plant income");
    }
}
