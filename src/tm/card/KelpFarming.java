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

public class KelpFarming extends Card {

    public KelpFarming() {
        super("Kelp Farming", 17, Tags.EMPTY);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getWaterCount() >= 6 - tolerance;
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT_2),
            new IncomeDeltaAction(new Resources(2, 0, 0, 3, 0, 0))
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 6 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 plants", "2 money income", "3 plant income");
    }
}
