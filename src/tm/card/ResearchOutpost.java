package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.effect.DiscountEffect;

public class ResearchOutpost extends Card implements DiscountEffect {

    public ResearchOutpost() {
        super("Research Outpost", 18, Tags.SCIENCE.combine(Tags.BUILDING).combine(Tags.CITY), true);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY, true);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money discount for all cards", "Place next to no other tile!");
    }

    @Override
    public int getDiscount(Card card) {
        return 1;
    }
}
