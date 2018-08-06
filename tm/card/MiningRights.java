package tm.card;

import java.awt.Graphics;
import java.awt.Point;

import javax.annotation.Nullable;
import tm.Card;
import tm.Game;
import tm.Renderer;
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
            public Point render(Graphics g, int x, int y, Game game) {
                Point p1 = Renderer.renderIcon(g, Tile.Type.MINING_RIGHTS, x, y);
                Resources.EMPTY.renderSteel(g, x + 3, y + 3, false, false);
                Resources.EMPTY.renderSteel(g, p1.x + 4, y + 3, true, false);
                Point p2 = Renderer.renderIcon(g, Tile.Type.MINING_RIGHTS, x, p1.y);
                Resources.EMPTY.renderTitanium(g, x + 3, p1.y + 3, false, false);
                Point p3 = Resources.EMPTY.renderTitanium(g, p2.x + 4, p1.y + 3, true, false);
                return new Point(p3.x, p2.y);
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
