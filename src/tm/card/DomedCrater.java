package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.ResourceDeltaAction;

public class DomedCrater extends Card {

    public DomedCrater() {
        super("Domed Crater", 24, new Tags().building().city());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() <= 7 + tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.CITY),
            new ResourceDeltaAction(Resources.PLANT_3),
            new IncomeDeltaAction(new Resources(3, 0, 0, 0, -1, 0))
        );
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 7% or less");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 plants", "3 money income", "-1 energy income");
    }
}
