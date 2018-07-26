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

public class Steelworks extends CardWithMarkers {

    private final Action action = new CardActionWithCost(true, ActionType.STEELWORKS, new Resources(0, 0, 0, 0, -4, 0)) {
        @Override
        protected Action getAction(Game game) {
            return new ActionChain(new AddOxygenAction(), new ResourceDeltaAction(Resources.STEEL_2));
        }
    };

    public Steelworks() {
        super("Steelworks", 15, Tags.BUILDING);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-4 energy", "1 oxygen", "2 steel");
    }
}
