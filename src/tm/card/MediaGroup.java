package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class MediaGroup extends Card implements PlayCardEffect {

    public MediaGroup() {
        super("Media Group", 6, Tags.EARTH, true);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("3 money for each event you play");
    }

    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.EVENT)) {
            return new ResourceDeltaAction(new Resources(3));
        }
        return null;
    }
}
