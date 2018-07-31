package tm.card;

import java.awt.Graphics;

import tm.Card;
import tm.Tags;
import tm.effect.RequirementEffect;

public class AdaptationTechnology extends Card implements RequirementEffect {

    public AdaptationTechnology() {
        super("Adaptation Technology", 12, Tags.SCIENCE, null, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        RequirementEffect.render(g, x, y, getTolerance());
    }

    @Override
    public int getTolerance() {
        return 2;
    }
}
