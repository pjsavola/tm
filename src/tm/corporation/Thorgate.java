package tm.corporation;

import java.awt.Graphics;

import tm.Card;
import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.effect.DiscountEffect;

public class Thorgate extends Corporation implements DiscountEffect {

    public Thorgate() {
        super("Thorgate", Tags.POWER, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(48);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        DiscountEffect.render(g, x, y, "images/tag_power.png", "-3");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.POWER)) {
            return 3;
        }
        return 0;
    }
}
