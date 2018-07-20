package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;

public class Capital extends Card {

    public Capital() {
        super("Capital", 26, new Tags().building().city());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getWaterCount() >= 4 - tolerance;
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.CAPITAL),
            new IncomeDeltaAction(new Resources(5, 0, 0, 0, -2, 0))
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("5 money income", "-2 energy income");
    }
}
