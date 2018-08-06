package tm.corporation;

import java.awt.Graphics;

import tm.Corporation;
import tm.Resources;
import tm.Tags;

public class Helion extends Corporation {

    public Helion() {
        super("Helion", Tags.SPACE, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(42);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 0, 3);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawString("May convert", x, y + 12);
        g.drawString("heat to money", x, y + 28);
    }
}
