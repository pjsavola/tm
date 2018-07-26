package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.AddWaterAction;

public class ArtificialLake extends Card {

    public ArtificialLake() {
        super("Artificial Lake", 15, Tags.BUILDING);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() >= -6 - 2 * tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddWaterAction(Tile.Type.ARTIFICIAL_LAKE);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -6C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 ocean to non-water tile");
    }
}
