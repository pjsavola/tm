package tm.card;

import java.awt.Graphics;

import tm.Card;
import tm.Tags;
import tm.effect.DiscountEffect;

public class EarthOffice extends Card implements DiscountEffect {

    public EarthOffice() {
        super("Earth Office", 1, Tags.EARTH, null, true);
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
