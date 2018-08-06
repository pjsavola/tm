package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Renderer;
import tm.Tags;

public class StandardTechnology extends Card {

    public StandardTechnology() {
        super("Standard Technology", 6, Tags.SCIENCE, null, true);
    }

    @Override
    public void renderEffect(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        final Point p = Renderer.renderText(g, "3 money for", x, y, false);
        Renderer.renderText(g, "standard projects", x, p.y + 4, false);
    }
}
