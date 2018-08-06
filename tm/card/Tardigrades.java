package tm.card;

import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;

public class Tardigrades extends CardWithMarkers {

    private final CardAction action = new CardAction(true, ActionType.TARDIGRADES) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Tardigrades.this);
        }
    };

    public Tardigrades() {
        super("Tardigrades", 4, Tags.MICROBE);
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 4;
    }

    @Override
    public String getRatio() {
        return "1:4";
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
