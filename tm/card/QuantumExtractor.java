package tm.card;

import java.awt.Graphics;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.effect.DiscountEffect;
import tm.requirement.TagRequirement;

public class QuantumExtractor extends Card implements DiscountEffect {

    public QuantumExtractor() {
        super("Quantum Extractor", 13, Tags.SCIENCE.combine(Tags.POWER), new TagRequirement(Tags.Type.SCIENCE.createTags(4)), true);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 4, 0);
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
