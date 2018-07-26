package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Tags;
import tm.effect.DiscountEffect;

public class AntiGravityTechnology extends Card implements DiscountEffect {

    public AntiGravityTechnology() {
        super("Anti-Gravity Technology", 14, Tags.SCIENCE, true);
    }

    @Override
    public boolean check(Player player) {
        return player.getTags().hasAll(Tags.Type.SCIENCE.createTags(7));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 7 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "2 money discount for all cards");
    }

    @Override
    public int getDiscount(Card card) {
        return 2;
    }
}
