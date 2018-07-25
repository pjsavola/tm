package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardActionWithCost;
import tm.action.MarkerDeltaAction;

public class PhysicsComplex extends CardWithMarkers {

    private final Action action = new CardActionWithCost(true, new Resources(0, 0, 0, 0, -6, 0)) {
        @Override
        public ActionType getType() {
            return ActionType.PHYSICS_COMPLEX;
        }

        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, PhysicsComplex.this);
        }
    };

    public PhysicsComplex() {
        super("Physics Complex", 12, Tags.SCIENCE.combine(Tags.BUILDING));
    }

    @Override
    public int getVPs() {
        return getMarkerCount() * 2;
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Spend 6 energy to add a marker", "2 VPs per marker", "Currently " + getMarkerCount() + " markers");
    }
}
