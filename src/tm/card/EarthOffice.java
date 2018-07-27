package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.effect.DiscountEffect;

public class EarthOffice extends Card implements DiscountEffect {

    public EarthOffice() {
        super("Earth Office", 1, Tags.EARTH, null, true);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "Cards with earth tag cost 3 less");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.EARTH)) {
            return 3;
        }
        return 0;
    }
}
