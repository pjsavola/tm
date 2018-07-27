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

public class NoctisFarming extends Card {

    public NoctisFarming() {
        super("Noctis Farming", 10, Tags.BUILDING.combine(Tags.PLANT), new TemperatureRequirement(-20, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT_2),
            new IncomeDeltaAction(Resources.MONEY)
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -20C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 plants", "1 money income");
    }
}
