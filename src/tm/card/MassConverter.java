package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.effect.DiscountEffect;
import tm.requirement.TagRequirement;

public class MassConverter extends Card implements DiscountEffect {

    public MassConverter() {
        super("Mass Converter", 8, Tags.SCIENCE.combine(Tags.POWER), new TagRequirement(Tags.Type.SCIENCE.createTags(5)),true);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 6, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 5 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "Space cards cost 2 less", "", "6 energy income");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.SPACE)) {
            return 2;
        }
        return 0;
    }
}
