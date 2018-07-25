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
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.CardActionWithCost;
import tm.action.ResourceDeltaAction;

public class Ironworks extends CardWithMarkers {

    private final Action action = new CardActionWithCost(true, new Resources(0, 0, 0, 0, -4, 0)) {
        @Override
        public ActionType getType() {
            return ActionType.IRONWORKS;
        }

        @Override
        protected Action getAction(Game game) {
            return new ActionChain(
                new AddOxygenAction(),
                new ResourceDeltaAction(Resources.STEEL)
            );
        }
    };

    public Ironworks() {
        super("Ironworks", 11, Tags.BUILDING);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-4 energy", "1 oxygen", "1 steel");
    }
}
