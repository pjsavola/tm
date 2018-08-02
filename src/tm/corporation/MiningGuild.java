package tm.corporation;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Corporation;
import tm.ImageCache;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.TileProperties;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.effect.PlaceTileEffect;

public class MiningGuild extends Corporation implements PlaceTileEffect {

    public MiningGuild() {
        super("Mining Guild", Tags.Type.BUILDING.createTags(2), true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(30, 5, 0, 0, 0, 0);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.STEEL;
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Get 1 steel income for covering steel or titanium");
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

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(Tile.Type.MINING_AREA.getIcon(), x, y, null);
        g.drawImage(ImageCache.getImage("images/icon_steel.png"), x + 4, y + 4, null);
        Resources.STEEL.render(g, x + 30, y + 4, true);
        g.drawImage(Tile.Type.MINING_AREA.getIcon(), x, y + 30, null);
        g.drawImage(ImageCache.getImage("images/icon_titanium.png"), x + 4, y + 34, null);
        Resources.STEEL.render(g, x + 30, y + 34, true);
    }
}
