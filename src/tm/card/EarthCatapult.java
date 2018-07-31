package tm.card;

import java.awt.Graphics;

import tm.Card;
import tm.Tags;
import tm.effect.DiscountEffect;

public class EarthCatapult extends Card implements DiscountEffect {

    public EarthCatapult() {
        super("Earth Catapult", 23, Tags.EARTH, null, true);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        DiscountEffect.render(g, x, y, "images/icon_card.png", "-2");
    }

    @Override
    public int getDiscount(Card card) {
        return 2;
    }
}
