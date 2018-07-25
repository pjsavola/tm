package tm.card;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.TileProperties;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.effect.PlaceTileEffect;

public class MiningArea extends Card implements PlaceTileEffect {

    public MiningArea() {
        super("Mining Area", 4, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.MINING_AREA);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Place tile on steel or titanium", "1 income of that type", "Must be placed next to your tile");
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (tile.getType() == Tile.Type.MINING_AREA) {
            final TileProperties p = tile.getProperties();
            if (p != null && (p.getSteel() > 0 || p.getTitanium() > 0)) {
                return new IncomeDeltaAction(p.getSteel() > 0 ? Resources.STEEL : Resources.TITANIUM);
            }
        }
        return null;
    }
}
