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
import tm.action.ResourceDeltaAction;

public class MartianRail extends Card {

    private final Action action = new CardActionWithCost(true, ActionType.MARTIAN_RAIL, Resources.ENERGY.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new ResourceDeltaAction(new Resources(game.getCityCount(true)));
        }
    };

    public MartianRail() {
        super("Martian Rail", 13, Tags.BUILDING);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "1 money for each city", "-1 energy");
    }
}
