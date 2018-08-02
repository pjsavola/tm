package tm.corporation;

import java.awt.Graphics;

import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.effect.ValueEffect;

public class Phoblog extends Corporation implements ValueEffect {

    public Phoblog() {
        super("Phoblog", Tags.SPACE, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(23, 0, 10, 0, 0, 0);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        ValueEffect.render(g, x, y, getSteelDelta(), getTitaniumDelta());
    }

    @Override
    public int getTitaniumDelta() {
        return 1;
    }
}
