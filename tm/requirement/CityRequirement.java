package tm.requirement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Game;
import tm.Renderer;
import tm.Tile;

public class CityRequirement implements Requirement {

    private final int limit;

    public CityRequirement(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean check(Game game, int tolerance) {
        return game.getCityCount(false) - limit + tolerance >= 0;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        final Point p1 = Renderer.renderSmallIcon(g, Tile.Type.CITY, x, y);
        Renderer.renderText(g, "≥ " + limit, p1.x + 3, y + 4, false);
    }
}
