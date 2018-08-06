package tm.card;

import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.CardWithMarkers;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.action.MarkerDeltaAction;

public class PhysicsComplex extends CardWithMarkers {

    private final CardAction action = new CardActionWithCost(true, ActionType.PHYSICS_COMPLEX, new Resources(0, 0, 0, 0, -6, 0)) {
        @Override
        protected Action getAction(Game game) {
            return new MarkerDeltaAction(1, PhysicsComplex.this);
        }
    };

    public PhysicsComplex() {
        super("Physics Complex", 12, Tags.SCIENCE_BUILDING);
    }

    @Override
    public int getVPs() {
        return getMarkerCount() * 2;
    }

    @Override
    public String getRatio() {
        return "2:1";
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
