package tm.requirement;

import java.awt.Graphics;

import tm.Game;

public interface Requirement {
    boolean check(Game game, int tolerance);
    void render(Graphics g, int x, int y);
}
