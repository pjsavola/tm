package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;
import tm.requirement.OxygenRequirement;

public class GHGProducingBacteria extends CardWithMarkers {

    private final CardAction action1 = new CardAction(true, getName()) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, GHGProducingBacteria.this);
        }
    };

    private final CardAction action2 = new CardAction(true, "1 temperature, -2 markers") {
        @Override
        protected Action getAction(Game game) {
            return new ActionChain(new AddTemperatureAction(), new MarkerDeltaAction(-2, GHGProducingBacteria.this));
        }
    };

    public GHGProducingBacteria() {
        super("GHG Producing Bacteria", 8, Tags.SCIENCE.combine(Tags.MICROBE), new OxygenRequirement(4, true));
        action1.setAlternativeAction(action2);
        action2.setAlternativeAction(action1);
    }

    @Override
    public List<CardAction> getActions() {
        return Arrays.asList(action1, action2);
    }
}
