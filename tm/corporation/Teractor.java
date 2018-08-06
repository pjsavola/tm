package tm.corporation;

import java.awt.Graphics;

import tm.Card;
import tm.Corporation;
import tm.Resources;
import tm.Tags;
import tm.effect.DiscountEffect;

public class Teractor extends Corporation implements DiscountEffect {

    public Teractor() {
        super("Teractor", Tags.EARTH, true);
    }

    @Override
    public Resources getResourceDelta() {
        return new Resources(60);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        DiscountEffect.render(g, x, y, "images/tag_earth.png", "-3");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.EARTH)) {
            return 3;
        }
        return 0;
    }
}
