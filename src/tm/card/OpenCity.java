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

public class OpenCity extends Card {

    public OpenCity() {
        super("Open City", 23, Tags.BUILDING.combine(Tags.CITY));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 12 - tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.CITY),
            new ResourceDeltaAction(Resources.PLANT_2),
            new IncomeDeltaAction(new Resources(4, 0, 0, 0, -1, 0))
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 12%");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("4 money income", "-1 energy income", "2 plants");
    }
}
