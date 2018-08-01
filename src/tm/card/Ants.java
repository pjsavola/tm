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

// Microbe is removed from dummy player
public class Ants extends CardWithMarkers {

    private final CardAction action = new CardAction(true, ActionType.ANTS) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Ants.this);
        }
    };

    public Ants() {
        super("Ants", 9, Tags.MICROBE, new OxygenRequirement(4, true));
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 2;
    }

    @Override
    public String getRatio() {
        return "1:2";
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 1 marker");
    }
}
