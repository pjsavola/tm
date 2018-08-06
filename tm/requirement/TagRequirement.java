package tm.requirement;

import java.awt.Color;
import java.awt.Graphics;

import tm.Game;
import tm.Renderer;
import tm.Tags;

public class TagRequirement implements Requirement {

    private final Tags tags;

    public TagRequirement(Tags tags) {
        this.tags = tags;
    }

    @Override
    public boolean check(Game game, int tolerance) {
        return game.getCurrentPlayer().getTags().hasAll(tags);
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(Color.RED);
        final int newX = Renderer.renderText(g, "Req.", x, y + 3, false).x + 3;
        tags.render(g, newX, y, true);
    }
}
