package tm.effect;

import java.awt.Graphics;

public interface RequirementEffect {
    int getTolerance();

    static void render(Graphics g, int x, int y, int tolerance) {
        g.drawString("+/- " + tolerance + " tolerance", x, y + 12);
    }
}
