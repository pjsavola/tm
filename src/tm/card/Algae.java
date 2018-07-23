package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class Algae extends Card {

    public Algae() {
        super("Algae", 10, new Tags().plant());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getWaterCount() >= 5 - tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT),
            new IncomeDeltaAction(Resources.PLANT_2)
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 5 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant", "2 plant income");
    }
}
