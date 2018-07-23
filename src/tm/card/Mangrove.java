package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.PlaceTileAction;

public class Mangrove extends Card {

    public Mangrove() {
        super("Mangrove", 12, Tags.PLANT);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() >= 4 - 2 * tolerance;
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.MANGROVE),
            new AddOxygenAction()
        );

    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Place greenery on ocean space", "(1 oxygen)");
    }
}
