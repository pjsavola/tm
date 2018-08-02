package tm.card;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.effect.DiscountEffect;
import tm.requirement.TagRequirement;

public class MassConverter extends Card implements DiscountEffect {

    public MassConverter() {
        super("Mass Converter", 8, Tags.SCIENCE.combine(Tags.POWER), new TagRequirement(Tags.Type.SCIENCE.createTags(5)),true);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 6, 0);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 5 science tags");
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
