package tm.card;

import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;

public class MoholeArea extends Card {

    public MoholeArea() {
        super("Mohole Area", 20, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 0, 4);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.MOHOLE_AREA) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = super.render(g, x, y, game);
                return Renderer.renderText(g, "on Ocean", p.x + 4, y + 4, false);
            }
        };
    }
}
