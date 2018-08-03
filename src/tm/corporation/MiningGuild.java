package tm.corporation;

import java.awt.Graphics;
import java.awt.Image;

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
        final Image image = Tile.Type.MINING_AREA.getIcon();
        final int width = image.getWidth(null);
        g.drawImage(image, x, y, null);
        g.drawImage(ImageCache.getImage("images/icon_steel.png"), x + 4, y + 4, null);
        Resources.STEEL.render(g, x + width + 4, y + 4, true);
        final int currentY = y + image.getHeight(null) + 4;
        g.drawImage(image, x, currentY, null);
        g.drawImage(ImageCache.getImage("images/icon_titanium.png"), x + 4, currentY + 4, null);
        Resources.STEEL.render(g, x + width + 4, currentY + 4, true);
    }
}
