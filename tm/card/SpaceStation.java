package tm.card;

import java.awt.Graphics;

import tm.Card;
import tm.Tags;
import tm.effect.DiscountEffect;

public class SpaceStation extends Card implements DiscountEffect {

    public SpaceStation() {
        super("Space Station", 10, Tags.SPACE, null, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        DiscountEffect.render(g, x, y, "images/tag_space.png", "-2");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.SPACE)) {
            return 2;
        }
        return 0;
    }
}
