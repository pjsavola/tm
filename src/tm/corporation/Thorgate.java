package tm.corporation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;
import tm.effect.DiscountEffect;

public class Thorgate extends Corporation implements DiscountEffect {

    public Thorgate() {
        super("Thorgate", Tags.POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(48)),
            new IncomeDeltaAction(Resources.ENERGY));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(ActionType.ENERGY, "Energy income",
            new ResourceDeltaAction(new Resources(-8)),
            new IncomeDeltaAction(Resources.ENERGY)
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("48 money", "1 energy income", "Everything with power tag costs 3 less");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.POWER)) {
            return 3;
        }
        return 0;
    }
}
