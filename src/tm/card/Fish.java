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

// Removing 1 plant income is done from dummy player
public class Fish extends CardWithMarkers {

    private final Action action = new CardAction(true) {
        @Override
        public ActionType getType() {
            return ActionType.FISH;
        }

        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Fish.this);
        }
    };

    public Fish() {
        super("Fish", 9, Tags.ANIMAL);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() >= 2 - 2 * tolerance;
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
        return Collections.singletonList("Requires 2C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 1 marker", "Each marker is worth 1 VP", "Currently " + getMarkerCount() + " markers");
    }
}
