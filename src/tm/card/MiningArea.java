package tm.card;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.ImageCache;
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
        return new PlaceTileAction(Tile.Type.MINING_AREA) {
            @Override
            public void render(Graphics g, int x, int y) {
                g.drawImage(ImageCache.getImage("images/icon_city.png"), x, y - 30, null);
                g.drawImage(ImageCache.getImage("images/icon_steel.png"), x + 4, y - 26, null);
                Resources.STEEL.render(g, x + 30, y - 26, true);
                g.drawImage(ImageCache.getImage("images/icon_city.png"), x, y, null);
                g.drawImage(ImageCache.getImage("images/icon_titanium.png"), x + 4, y + 4, null);
                Resources.TITANIUM.render(g, x + 30, y + 4, true);
            }
        };
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
