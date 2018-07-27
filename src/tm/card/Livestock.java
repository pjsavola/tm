package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.IncomeDeltaAction;
import tm.action.MarkerDeltaAction;
import tm.requirement.OxygenRequirement;

public class Livestock extends CardWithMarkers {

    private final Action action = new CardAction(true, ActionType.LIVESTOCK) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Livestock.this);
        }
    };

    public Livestock() {
        super("Livestock", 13, Tags.ANIMAL, new OxygenRequirement(9, true));
    }

    @Override
    public int getVPs() {
        return getMarkerCount();
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(2, 0, 0, -1, 0, 0));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 9% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 1 marker", "Each marker is worth 1 VP", "Currently " + getMarkerCount() + " markers", "", "2 money income", "-1 plant income");
    }
}
