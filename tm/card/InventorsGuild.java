package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.DrawCardsAction;

public class InventorsGuild extends Card {

    private final CardAction action = new CardAction(false, "Buy 1 card") {
        @Override
        protected Action getAction(Game game) {
            return new DrawCardsAction(1, true, false);
        }
    };

    public InventorsGuild() {
        super("Inventors' Guild", 9, Tags.SCIENCE);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
