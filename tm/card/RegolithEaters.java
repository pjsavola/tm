package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;

public class RegolithEaters extends CardWithMarkers {

    private final CardAction action1 = new CardAction(true, getName()) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, RegolithEaters.this);
        }
    };

    private final CardAction action2 = new CardAction(true, "1 oxygen, -2 markers") {
        @Override
        protected Action getAction(Game game) {
            return new ActionChain(new AddOxygenAction(), new MarkerDeltaAction(-2, RegolithEaters.this));
        }
    };

    public RegolithEaters() {
        super("Regolith Eaters", 13, Tags.SCIENCE.combine(Tags.MICROBE));
        action1.setAlternativeAction(action2);
        action2.setAlternativeAction(action1);
    }

    @Override
    public List<CardAction> getActions() {
        return Arrays.asList(action1, action2);
    }
}
