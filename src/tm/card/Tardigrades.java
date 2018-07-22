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

public class Tardigrades extends CardWithMarkers {

    private final CardAction action = new CardAction(true) {
        @Override
        public ActionType getType() {
            return ActionType.TARDIGRADES;
        }

        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, Tardigrades.this);
        }
    };

    public Tardigrades() {
        super("Tardigrades", 4, new Tags().microbe());
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 4;
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Add 1 marker", "Each 4 markers is worth 1 VP", "Currently " + getMarkerCount() + " markers");
    }
}
