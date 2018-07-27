package tm.requirement;

import java.awt.Graphics;

import tm.Game;

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

    }
}
