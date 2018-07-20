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
import tm.action.ActionChain;
import tm.action.CardAction;
import tm.action.DrawCardsAction;
import tm.action.ResourceDeltaAction;

public class DevelopmentCenter extends Card {

    private final Action action = new CardAction(false) {
        @Override
        protected Action getAction(Game game) {
            return new DrawCardsAction(1, false, false);
        }
    };

    public DevelopmentCenter() {
        super("Development Center", 11, new Tags().building().science());
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(
            ActionType.DEVELOPMENT_CENTER,
            getName(),
            new ResourceDeltaAction(new Resources(0, 0, 0, 0, -1, 0)),
            action
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-1 energy", "Draw 1 card");
    }
}
