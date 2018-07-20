package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;

public class ResearchOutpost extends Card {

    public ResearchOutpost() {
        super("Research Outpost", 18, new Tags().science().building().city(), true);
    }

    @Override
    public Action getInitialAction() {
        return new PlaceTileAction(Tile.Type.CITY, true);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money discount for all cards", "Place next to no other tile!");
    }
}
