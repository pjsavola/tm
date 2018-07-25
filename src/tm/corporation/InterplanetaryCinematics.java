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
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class InterplanetaryCinematics extends Corporation implements PlayCardEffect {

    public InterplanetaryCinematics() {
        super("Interplanetary Cinematics", Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources(30, 20, 0, 0, 0, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("30 money", "20 steel", "Get 2 money for each event you play");
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.EVENT)) {
            return new ResourceDeltaAction(new Resources(2));
        }
        return null;
    }
}
