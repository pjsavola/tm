package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.DrawCardsAction;

public class InventorsGuild extends Card {

    private final Action action = new CardAction(false) {
        @Override
        public ActionType getType() {
            return ActionType.INVENTORS_GUILD;
        }

        @Override
        protected Action getAction(Game game) {
            return new DrawCardsAction(1, true, false);
        }
    };

    public InventorsGuild() {
        super("Inventors' Guild", 9, new Tags().science());
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Draw card and discard or buy it");
    }
}
