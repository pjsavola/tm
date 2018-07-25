package tm.corporation;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.TileProperties;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;
import tm.effect.PlaceTileEffect;

public class MiningGuild extends Corporation implements PlaceTileEffect {

    public MiningGuild() {
        super("Mining Guild", Tags.Type.BUILDING.createTags(2));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(30, 5, 0, 0, 0, 0)),
            new IncomeDeltaAction(Resources.STEEL)
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("30 money", "5 steel", "1 steel income", "Get 1 steel income for covering steel or titanium");
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        final TileProperties p = tile.getProperties();
        if (p != null && (p.getSteel() > 0 || p.getTitanium() > 0)) {
            return new IncomeDeltaAction(Resources.STEEL);
        }
        return null;
    }
}
