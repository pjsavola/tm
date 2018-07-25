package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Tags;
import tm.effect.DiscountEffect;

public class SpaceStation extends Card implements DiscountEffect {

    public SpaceStation() {
        super("Space Station", 10, Tags.SPACE, true);
    }

    @Override
    public boolean check(Player player) {
        return player.getTags().has(Tags.Type.MICROBE);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires microbe tag");
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
