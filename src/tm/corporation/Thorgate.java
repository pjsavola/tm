package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.effect.DiscountEffect;

public class Thorgate extends Corporation implements DiscountEffect {

    public Thorgate() {
        super("Thorgate", Tags.POWER);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(48);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.ENERGY);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 energy income", "Everything with power tag costs 3 less");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.POWER)) {
            return 3;
        }
        return 0;
    }
}
