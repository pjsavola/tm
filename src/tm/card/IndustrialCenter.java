package tm.card;

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
import tm.action.PlaceTileAction;

public class IndustrialCenter extends Card {

    private final CardAction action = new CardActionWithCost(true, ActionType.INDUSTRIAL_CENTER, new Resources(-7), Resources.STEEL);
    public IndustrialCenter() {
        super("Industrial Center", 4, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.INDUSTRIAL_CENTER);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Place next to a city tile");
    }
}
