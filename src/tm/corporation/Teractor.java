package tm.corporation;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.effect.DiscountEffect;

public class Teractor extends Corporation implements DiscountEffect {

    public Teractor() {
        super("Teractor", Tags.EARTH);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(60);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Cards with earth tag cost 3 less");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.EARTH)) {
            return 3;
        }
        return 0;
    }
}
