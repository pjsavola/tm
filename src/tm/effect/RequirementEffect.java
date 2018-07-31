package tm.effect;

import java.awt.Color;
import java.awt.Graphics;

import tm.Card;

public interface RequirementEffect {
    int getTolerance();

    static void render(Graphics g, int x, int y, int tolerance) {
        g.setFont(Card.FONT);
        g.setColor(Color.WHITE);
        g.drawString("Effect:", x, y);
        g.drawString("+/- " + tolerance + " tolerance", x, y + 17);
    }
}
