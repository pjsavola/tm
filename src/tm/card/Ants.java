package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Planet;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;

// Microbe is removed from dummy player
public class Ants extends CardWithMarkers {

    private final CardAction action = new CardAction(true) {
        @Override
        public ActionType getType() {
            return ActionType.ANTS;
        }

        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Ants.this);
        }
    };

    public Ants() {
        super("Ants", 9, new Tags().microbe());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 4 - tolerance;
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 2;
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 1 marker", "Each 2 markers is worth 1 VP", "Currently " + getMarkerCount() + " markers");
    }
}
