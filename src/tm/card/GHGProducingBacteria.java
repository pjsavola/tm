package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Planet;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.CardAction;
import tm.action.MarkerDeltaAction;

public class GHGProducingBacteria extends CardWithMarkers {

    private final CardAction action1 = new CardAction(true) {
        @Override
        public ActionType getType() {
            return ActionType.GHG_PRODUCING_BACTERIA_1;
        }

        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, GHGProducingBacteria.this);
        }
    };

    private final CardAction action2 = new CardAction(true) {
        @Override
        protected Action getAction(Game game) {
            return new AddTemperatureAction();
        }
    };

    public GHGProducingBacteria() {
        super("GHG Producing Bacteria", 8, new Tags().science().microbe());
        action1.setAlternativeAction(action2);
        action2.setAlternativeAction(action1);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 4 - tolerance;
    }

    @Override
    public List<Action> getActions() {
        return Arrays.asList(
            action1,
            new ActionChain(
                ActionType.GHG_PRODUCING_BACTERIA_2,
                getName(),
                new MarkerDeltaAction(-2, this),
                action2
            )
        );
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action 1:", "Add 1 marker", "", "Action 2:", "Remove 2 markers to raise temperature", "", "Currently " + getMarkerCount() + " markers");
    }
}
