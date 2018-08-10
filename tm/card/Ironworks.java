package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddOxygenAction;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class Ironworks extends Card {

    private final CardAction action = new CardActionWithCost(true, "1 steel, -4 energy, 1 oxygen", new Resources(0, 1, 0, 0, -4, 0)) {
        @Override
        protected Action getAction(Game game) {
            return new AddOxygenAction();
        }
    };

    public Ironworks() {
        super("Ironworks", 11, Tags.BUILDING);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
