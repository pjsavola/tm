package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddOxygenAction;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.requirement.OceanRequirement;

public class WaterSplittingPlant extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.WATER_SPLITTING_PLANT, new Resources(0, 0, 0, 0, -3, 0)) {
        @Override
        protected Action getAction(Game game) {
            return new AddOxygenAction();
        }
    };

    public WaterSplittingPlant() {
        super("Water Splitting Plant", 12, Tags.BUILDING, new OceanRequirement(2, true));
    }

    @Override
    public List<CardAction> getActions() {
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
