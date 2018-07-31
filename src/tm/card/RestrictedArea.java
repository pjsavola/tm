package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.action.DrawCardsAction;
import tm.action.PlaceTileAction;

public class RestrictedArea extends Card {

    private final CardAction action = new CardActionWithCost(false, ActionType.RESTRICTED_AREA, new Resources(-2)) {
        @Override
        protected Action getAction(Game game) {
            return new DrawCardsAction(1, false, false);
        }
    };

    public RestrictedArea() {
        super("Restricted Area", 11, Tags.SCIENCE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.RESTRICTED_AREA);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Spend 2 money to draw a card", "", "Place restricted area tile");
    }
}
