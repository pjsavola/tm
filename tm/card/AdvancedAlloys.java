package tm.card;

import java.awt.Graphics;

import tm.Card;
import tm.Tags;
import tm.effect.ValueEffect;

public class AdvancedAlloys extends Card implements ValueEffect {

    public AdvancedAlloys() {
        super("Advanced Alloys", 9, Tags.SCIENCE, null, true);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        ValueEffect.render(g, x, y, getSteelDelta(), getTitaniumDelta());
    }

    @Override
    public int getSteelDelta() {
        return 1;
    }

    @Override
    public int getTitaniumDelta() {
        return 1;
    }
}
