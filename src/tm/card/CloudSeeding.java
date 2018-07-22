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
import tm.action.IncomeDeltaAction;

// Decrease any heat income by 1 is done from dummy player
public class CloudSeeding extends Card {

    public CloudSeeding() {
        super("Cloud Seeding", 11, new Tags());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getWaterCount() >= 3 - tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(-1, 0, 0, 2, 0, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-1 money income", "2 plant income");
    }
}
