package tm.card;

import java.util.Collections;
import java.util.List;

import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;
import tm.requirement.OxygenRequirement;

// Removing 1 animal is done from dummy player
public class Predators extends CardWithMarkers {

    private final CardAction action = new CardAction(true, getName()) {
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
    public String getRatio() {
        return "1:1";
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
