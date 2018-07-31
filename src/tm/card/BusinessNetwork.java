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
import tm.action.CardAction;
import tm.action.DrawCardsAction;

public class BusinessNetwork extends Card {

    private final CardAction action = new CardAction(false, ActionType.BUSINESS_NETWORK) {
        @Override
        protected Action getAction(Game game) {
            return new DrawCardsAction(1, true, false);
        }
    };

    public BusinessNetwork() {
        super("Business Network", 4, Tags.EARTH);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.MONEY.negate();
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Draw card and discard or buy it");
    }
}
