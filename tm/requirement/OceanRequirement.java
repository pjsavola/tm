package tm.requirement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Game;
import tm.Renderer;
import tm.Tile;

public class OceanRequirement implements Requirement {

    private final int limit;
    private final boolean min;

    public OceanRequirement(int limit, boolean min) {
        this.limit = limit;
        this.min = min;
    }

    @Override
    public boolean check(Game game, int tolerance) {
        final int ocean = game.getPlanet().getWaterPlaced();
        final int diff = min ? (ocean - limit) : (limit - ocean);
        return diff + tolerance >= 0;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        final Point p1 = Renderer.renderSmallIcon(g, Tile.Type.WATER, x, y);
        Renderer.renderText(g, (min ? "≥" : "≤") + " " + limit, p1.x + 3, y + 4, false);
    }
}
