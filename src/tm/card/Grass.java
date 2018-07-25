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

public class Grass extends Card {

    public Grass() {
        super("Grass", 11, Tags.PLANT);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() >= -16 - 2 * tolerance;
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
