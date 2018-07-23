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

public class Trees extends Card {

    public Trees() {
        super("Trees", 13, new Tags().plant());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() >= -4 - 2 * tolerance;
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