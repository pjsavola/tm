package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Lichen extends Card {

    public Lichen() {
        super("Lichen", 7, Tags.PLANT);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() >= -24 - 2 * tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.PLANT);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -24C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 plant income");
    }
}
