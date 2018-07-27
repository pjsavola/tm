package tm.card;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class OptimalAerobraking extends Card implements PlayCardEffect {

    public OptimalAerobraking() {
        super("Optimal Aerobraking", 7, Tags.SPACE, null, true);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "Gain 3 money and 3 heat for each space event");
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.EVENT) && card.getTags().has(Tags.Type.SPACE)) {
            return new ResourceDeltaAction(new Resources(3, 0, 0, 0, 0, 3));
        }
        return null;
    }
}
