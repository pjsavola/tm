package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
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
    public Action getInitialAction() {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(0, 0, 0, 1, 0, 0)),
            new IncomeDeltaAction(new Resources(0, 0, 0, 2, 0, 0))
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
