package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.effect.ScoringEffect;

public class CommercialDistrict extends Card implements ScoringEffect {

    public CommercialDistrict() {
        super("Commercial District", 16, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(4, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.COMMERCIAL_DISTRICT);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Place tile", "1 vp per adjacent city tile");
    }

    @Override
    public int getVPs(Player player) {
        final Tile tile = player.getCommercialDistrict();
        if (tile != null) {
            return (int) tile.getNeighbors().stream().filter(Tile::isCity).count();
        } else {
            return 0;
        }
    }
}
