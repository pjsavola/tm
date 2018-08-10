package tm.card;

import java.util.Collections;
import java.util.List;

import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;
import tm.requirement.TemperatureRequirement;

// Removing 1 plant income is done from dummy player
public class Fish extends CardWithMarkers {

    private final CardAction action = new CardAction(true, getName()) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Fish.this);
        }
    };

    public Fish() {
        super("Fish", 9, Tags.ANIMAL, new TemperatureRequirement(2, true));
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
