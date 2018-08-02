package tm.corporation;

import java.awt.Graphics;

import tm.Corporation;
import tm.Resources;
import tm.Tags;

public class Ecoline extends Corporation {

    public Ecoline() {
        super("Ecoline", Tags.PLANT, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(36, 0, 0, 3, 0, 0);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT_2;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawString("Greenery costs 7", x, y + 12);
    }
}
