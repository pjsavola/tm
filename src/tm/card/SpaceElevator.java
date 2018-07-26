package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardActionWithCost;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class SpaceElevator extends Card {

    private final Action action = new CardActionWithCost(true, ActionType.SPACE_ELEVATOR, Resources.STEEL.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(new Resources(5));
        }
    };

    public SpaceElevator() {
        super("Space Elevator", 27, Tags.BUILDING.combine(Tags.SCIENCE));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.TITANIUM);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "5 money", "-1 steel", "", "1 titanium income");
    }
}
