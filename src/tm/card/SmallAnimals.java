package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;
import tm.requirement.OxygenRequirement;

// Removing 1 plant income is done from dummy player
public class SmallAnimals extends CardWithMarkers {

    private final CardAction action = new CardAction(true, ActionType.SMALL_ANIMALS) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, SmallAnimals.this);
        }
    };

    public SmallAnimals() {
        super("Small Animals", 6, Tags.ANIMAL, new OxygenRequirement(6, true));
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 2;
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 6% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 1 marker", "Each 2 markers is worth 1 VP", "Currently " + getMarkerCount() + " markers");
    }
}
