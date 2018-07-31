package tm.card;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.effect.DiscountEffect;
import tm.requirement.TagRequirement;

public class AntiGravityTechnology extends Card implements DiscountEffect {

    public AntiGravityTechnology() {
        super("Anti-Gravity Technology", 14, Tags.SCIENCE, new TagRequirement(Tags.Type.SCIENCE.createTags(7)), true);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 7 science tags");
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
