package tm.card;

import java.util.Collections;
import java.util.List;

import tm.CardWithMarkers;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.action.MarkerDeltaAction;

// Removing 1 animal is done from dummy player
public class SecurityFleet extends CardWithMarkers {

    private final CardAction action = new CardActionWithCost(true, getName(), Resources.TITANIUM.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, SecurityFleet.this);
        }
    };

    public SecurityFleet() {
        super("Security Fleet", 12, Tags.SPACE);
    }

    @Override
    public int getVPs() {
        return getMarkerCount();
    }

    @Override
    public String getRatio() {
        return "1:1";
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
