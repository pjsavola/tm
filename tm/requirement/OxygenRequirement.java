package tm.requirement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import tm.Game;
import tm.ImageCache;
import tm.Renderer;

public class OxygenRequirement implements Requirement {

    private final int limit;
    private final boolean min;

    public OxygenRequirement(int limit, boolean min) {
        this.limit = limit;
        this.min = min;
    }

    @Override
    public boolean check(Game game, int tolerance) {
        final int oxygen = game.getPlanet().getOxygen();
        final int diff = min ? (oxygen - limit) : (limit - oxygen);
        return diff + tolerance >= 0;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        final Image image = ImageCache.getImage("images/icon_oxygen.png").getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        g.drawImage(image, x, y, null);
        Renderer.renderText(g, (min ? "≥" : "≤") + " " + limit + "%", x + 19, y + 4, false);
    }
}
