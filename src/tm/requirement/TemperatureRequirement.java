package tm.requirement;

import java.awt.Graphics;

import tm.Game;

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

    }
}
