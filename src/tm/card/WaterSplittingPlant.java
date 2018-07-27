package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddOxygenAction;
import tm.action.CardActionWithCost;

public class WaterSplittingPlant extends CardWithMarkers {

    private final Action action = new CardActionWithCost(true, ActionType.WATER_SPLITTING_PLANT, new Resources(0, 0, 0, 0, -3, 0)) {
        @Override
        protected Action getAction(Game game) {
            return new AddOxygenAction();
        }
    };

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getWaterPlaced() >= 2 - tolerance;
    }

    public WaterSplittingPlant() {
        super("Water Splitting Plant", 12, Tags.BUILDING);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2 ocean tiles");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-3 energy", "1 oxygen");
    }
}
