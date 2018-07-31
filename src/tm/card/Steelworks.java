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
import tm.action.AddOxygenAction;
import tm.action.CardAction;
import tm.action.CardActionWithCost;

public class Steelworks extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.STEELWORKS, new Resources(0, 2, 0, 0, -4, 0)) {
        @Override
        protected Action getAction(Game game) {
            return new AddOxygenAction();
        }
    };

    public Steelworks() {
        super("Steelworks", 15, Tags.BUILDING);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "-4 energy", "1 oxygen", "2 steel");
    }
}
