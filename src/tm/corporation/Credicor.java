package tm.corporation;

import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class Credicor extends Corporation implements PlayCardEffect {

    public Credicor() {
        super("Credicor", Tags.EMPTY);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(57);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Get 4 money for value of 20 or more");
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getCost() >= 20) {
            return new ResourceDeltaAction(new Resources(4));
        }
        return null;
    }
}
