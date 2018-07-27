package tm.requirement;

import java.awt.Graphics;

import tm.Game;

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

    }
}
