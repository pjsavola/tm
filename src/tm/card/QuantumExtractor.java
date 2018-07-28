package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.effect.DiscountEffect;
import tm.requirement.TagRequirement;

public class QuantumExtractor extends Card implements DiscountEffect {

    public QuantumExtractor() {
        super("Quantum Extractor", 13, Tags.SCIENCE.combine(Tags.POWER), new TagRequirement(Tags.Type.SCIENCE.createTags(4)), true);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, 4, 0);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "Space cards cost 2 less");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.SPACE)) {
            return 2;
        }
        return 0;
    }
}
