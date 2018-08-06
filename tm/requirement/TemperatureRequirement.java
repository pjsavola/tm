package tm.requirement;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import tm.Game;
import tm.ImageCache;
import tm.Renderer;

public class TemperatureRequirement implements Requirement {

    private final int limit;
    private final boolean min;

    public TemperatureRequirement(int limit, boolean min) {
        this.limit = limit;
        this.min = min;
    }

    @Override
    public boolean check(Game game, int tolerance) {
        final int temperature = game.getPlanet().getTemperature();
        final int diff = min ? (temperature - limit) : (limit - temperature);
        return diff + 2 * tolerance >= 0;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        final Image image = ImageCache.getImage("images/icon_temperature.png").getScaledInstance(8, 20, Image.SCALE_DEFAULT);
        g.drawImage(image, x - 2, y, null);
        Renderer.renderText(g, (min ? "≥" : "≤") + " " + limit + "°C", x + 11, y + 4, false);
    }
}
