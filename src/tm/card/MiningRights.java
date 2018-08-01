package tm.card;

import java.awt.Graphics;
import java.awt.Image;

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

public class MiningRights extends Card implements PlaceTileEffect {

    public MiningRights() {
        super("Mining Rights", 9, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.MINING_RIGHTS) {
            @Override
            public void render(Graphics g, int x, int y, Game game) {
                g.drawImage(Tile.Type.MINING_AREA.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT), x, y - 30, null);
                g.drawImage(ImageCache.getImage("images/icon_steel.png"), x + 4, y - 26, null);
                Resources.STEEL.render(g, x + 30, y - 26, true);
                g.drawImage(Tile.Type.MINING_AREA.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT), x, y, null);
                g.drawImage(ImageCache.getImage("images/icon_titanium.png"), x + 4, y + 4, null);
                Resources.TITANIUM.render(g, x + 30, y + 4, true);
            }
        };
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (tile.getType() == Tile.Type.MINING_RIGHTS) {
            final TileProperties p = tile.getProperties();
            if (p != null && (p.getSteel() > 0 || p.getTitanium() > 0)) {
                return new IncomeDeltaAction(p.getSteel() > 0 ? Resources.STEEL : Resources.TITANIUM);
            }
        }
        return null;
    }
}
