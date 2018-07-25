package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;
import tm.effect.PlaceTileEffect;

public class ArcticAlgae extends Card implements PlaceTileEffect {

    public ArcticAlgae() {
        super("Arctic Algae", 12, Tags.PLANT, true);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() <= -12 + tolerance * 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.PLANT);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -12C or colder");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant", "2 plants for each ocean");
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (tile.getType() == Tile.Type.WATER) {
            return new ResourceDeltaAction(Resources.PLANT_2);
        }
        return null;
    }
}
