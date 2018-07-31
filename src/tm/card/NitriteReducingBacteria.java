package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTerraformingRatingAction;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;

public class NitriteReducingBacteria extends CardWithMarkers {

    private final CardAction action1 = new CardAction(true, ActionType.NITRITE_REDUCING_BACTERIA_1) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, NitriteReducingBacteria.this);
        }
    };

    private final CardAction action2 = new CardAction(true, ActionType.NITRITE_REDUCING_BACTERIA_2) {
        @Override
        protected Action getAction(Game game) {
            return new ActionChain(new AddTerraformingRatingAction(), new MarkerDeltaAction(-3, NitriteReducingBacteria.this));
        }
    };

    public NitriteReducingBacteria() {
        super("Nitrite Reducing Bacteria", 11, Tags.MICROBE);
        action1.setAlternativeAction(action2);
        action2.setAlternativeAction(action1);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new MarkerDeltaAction(3, this);
    }

    @Override
    public List<CardAction> getActions() {
        return Arrays.asList(action1, action2);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action 1:", "Add 1 marker", "", "Action 2:", "Remove 3 markers to get TR", "", "Currently " + getMarkerCount() + " markers");
    }
}
