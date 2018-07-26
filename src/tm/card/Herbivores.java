package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.CardWithMarkers;
import tm.Game;
import tm.Planet;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.MarkerDeltaAction;
import tm.effect.PlaceTileEffect;

// Removing 1 plant income is done from dummy player
public class Herbivores extends CardWithMarkers implements PlaceTileEffect {

    public Herbivores() {
        super("Herbivores", 12, Tags.ANIMAL, true);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 8 - tolerance;
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new MarkerDeltaAction(1, this);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 8% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "Add 1 marker when placing greenery tile", "Each 2 markers is worth 1 VP", "Currently " + getMarkerCount() + " markers", "", "1 marker");
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (tile.isGreenery()) {
            return new MarkerDeltaAction(1, this);
        }
        return null;
    }
}
