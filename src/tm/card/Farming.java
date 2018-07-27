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

public class Farming extends Card {

    public Farming() {
        super("Farming", 16, Tags.MICROBE, new TemperatureRequirement(4, true));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT_2),
            new IncomeDeltaAction(new Resources(2, 0, 0, 2, 0, 0))
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires +4C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 plants", "2 money income", "2 plant income");
    }
}
