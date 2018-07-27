package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Tags;
import tm.action.Action;
import tm.action.MarkerDeltaAction;
import tm.effect.PlayCardEffect;
import tm.requirement.OxygenRequirement;

public class Decomposers extends CardWithMarkers implements PlayCardEffect {

    public Decomposers() {
        super("Decomposers", 5, Tags.MICROBE, new OxygenRequirement(3, true), true);
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 3;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 3%");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "When you play card with", "plant, animal or microbe tag", "(including this)", "Place marker on this card", "1 vp for each 3 markers", "Currently " + getMarkerCount() + " markers");
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        int markers = 0;
        if (card.getTags().has(Tags.Type.ANIMAL)) {
            markers++;
        }
        if (card.getTags().has(Tags.Type.PLANT)) {
            markers++;
        }
        if (card.getTags().has(Tags.Type.MICROBE)) {
            markers++;
        }
        if (markers > 0) {
            return new MarkerDeltaAction(markers, this);
        }
        return null;
    }
}
