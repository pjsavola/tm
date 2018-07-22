package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Game;
import tm.Planet;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.CardWithMarkers;
import tm.action.MarkerDeltaAction;

// Removing 1 animal is done from dummy player
public class Predators extends CardWithMarkers {

    private final Action action = new CardAction(true) {
        @Override
        public ActionType getType() {
            return ActionType.PREDATORS;
        }

        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Predators.this);
        }
    };

    public Predators() {
        super("Predators", 14, new Tags().animal());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 11 - tolerance;
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
