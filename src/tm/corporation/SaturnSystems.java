package tm.corporation;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.effect.PlayCardEffect;

public class SaturnSystems extends Corporation implements PlayCardEffect {

    public SaturnSystems() {
        super("Saturn Systems", Tags.JOVIAN);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(42);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(1, 0, 1, 0, 0, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 titanium income", "1 money income for each Jovian tag");
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.JOVIAN)) {
            return new IncomeDeltaAction(Resources.MONEY);
        }
        return null;
    }
}
