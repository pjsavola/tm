package tm.requirement;

import java.awt.Graphics;

import tm.Game;

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

    }
}
