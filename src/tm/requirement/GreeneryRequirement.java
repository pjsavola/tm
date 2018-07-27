package tm.requirement;

import java.awt.Graphics;

import tm.Game;

public class GreeneryRequirement implements Requirement {

    @Override
    public boolean check(Game game, int tolerance) {
        return game.getCurrentPlayer().getGreeneryCount() > 0;
    }

    @Override
    public void render(Graphics g, int x, int y) {

    }
}
