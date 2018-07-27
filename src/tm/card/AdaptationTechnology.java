package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.effect.RequirementEffect;

public class AdaptationTechnology extends Card implements RequirementEffect {

    public AdaptationTechnology() {
        super("Adaptation Technology", 12, Tags.SCIENCE, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "+/- 2 to card requirements");
    }

    @Override
    public int getTolerance() {
        return 2;
    }
}
