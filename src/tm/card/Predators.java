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

// Removing 1 animal is done from dummy player
public class Predators extends CardWithMarkers {

    private final Action action = new CardAction(true, ActionType.PREDATORS) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Predators.this);
        }
    };

    public Predators() {
        super("Predators", 14, Tags.ANIMAL, new OxygenRequirement(11, true));
    }

    @Override
    public int getVPs() {
        return getMarkerCount();
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 11% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 1 marker", "Each marker is worth 1 VP", "Currently " + getMarkerCount() + " markers");
    }
}
