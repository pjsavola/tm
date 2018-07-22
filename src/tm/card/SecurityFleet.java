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

// Removing 1 animal is done from dummy player
public class SecurityFleet extends CardWithMarkers {

    private final Action action = new CardAction(true) {
        @Override
        public ActionType getType() {
            return ActionType.SECURITY_FLEET;
        }

        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, SecurityFleet.this);
        }
    };

    public SecurityFleet() {
        super("Security Fleet", 12, new Tags().space());
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
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Spend 1 titanium to add 1 marker", "Each marker is worth 1 VP", "Currently " + getMarkerCount() + " markers");
    }
}
