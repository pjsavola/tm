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

public class GreatDam extends Card {

    public GreatDam() {
        super("Great Dam", 12, Tags.BUILDING.combine(Tags.POWER));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getWaterPlaced() >= 4 - tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 2, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 energy income");
    }
}