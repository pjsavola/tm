package tm.requirement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Game;
import tm.Renderer;
import tm.Tile;

public class GreeneryRequirement implements Requirement {

    @Override
    public boolean check(Game game, int tolerance) {
        return game.getCurrentPlayer().getGreeneryCount() > 0;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        final Point p1 = Renderer.renderSmallIcon(g, Tile.Type.GREENERY, x, y);
        g.fillRect(x + 6, y + 6, 4, 4);
        Renderer.renderText(g, "≥ 1", p1.x + 3, y + 4, false);
    }
}
