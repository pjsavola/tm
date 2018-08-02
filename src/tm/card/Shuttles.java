package tm.card;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.effect.DiscountEffect;
import tm.requirement.OxygenRequirement;

public class Shuttles extends Card implements DiscountEffect {

    public Shuttles() {
        super("Shuttles", 10, Tags.SPACE, new OxygenRequirement(5, true), true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(2, 0, 0, 0, -1, 0);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires at least 5% oxygen");
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
