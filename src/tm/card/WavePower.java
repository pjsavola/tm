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

public class WavePower extends Card {

    public WavePower() {
        super("Wave Power", 8, Tags.POWER);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getWaterPlaced() >= 3 - tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.ENERGY);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 energy income");
    }
}
