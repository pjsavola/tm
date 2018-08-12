package tm.card;

import java.awt.Color;
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

public class UrbanizedArea extends Card {

    public UrbanizedArea() {
        super("Urbanized Area", 10, Tags.BUILDING_CITY);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.URBANIZED_AREA) {
            @Override
            public Point render(Graphics g, int x, int y, Game game) {
                final Point p = super.render(g, x, y, game);
                g.setColor(Color.WHITE);
                return Renderer.renderText(g, "adjacent to 2 cities", p.x + 4, y + 4, false);
            }
        };
    }
}
