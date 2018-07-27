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
import tm.action.DrawCardsAction;

public class DevelopmentCenter extends Card {

    private final Action action = new CardActionWithCost(false, ActionType.DEVELOPMENT_CENTER, Resources.ENERGY.negate()) {
        @Override
        protected Action getAction(Game game) {
            return new DrawCardsAction(1, false, false);
        }
    };

    public DevelopmentCenter() {
        super("Development Center", 11, Tags.SCIENCE_BUILDING);
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-1 energy", "Draw 1 card");
    }
}
