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

public class UndergroundDetonations extends Card {

    private final Action action = new CardActionWithCost(true, ActionType.UNDERGROUND_DETONATIONS, new Resources(-10)) {
        @Override
        protected Action getAction(Game game) {
            return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 0, 2));
        }
    };

    public UndergroundDetonations() {
        super("Underground Detonations", 6, Tags.BUILDING);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "10 money", "-2 heat income");
    }
}
