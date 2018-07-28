package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddMarkerAction;
import tm.action.ResourceDeltaAction;
import tm.effect.PlayCardEffect;

public class ViralEnhancers extends Card implements PlayCardEffect {

    public ViralEnhancers() {
        super("Viral Enhancers", 9, Tags.SCIENCE.combine(Tags.MICROBE), null, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "When playing card with animal/plant/microbe tag", "(including this)", "add marker to that card or gain 1 plant");
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card.getTags().has(Tags.Type.ANIMAL) || card.getTags().has(Tags.Type.PLANT) || card.getTags().has(Tags.Type.MICROBE)) {
            return new AddMarkerAction(Collections.singletonList(card)) {
                @Override
                protected String getTitle() {
                    return "Select card for marker or gain 1 plant";
                }

                @Override
                protected Action onEmptySelection() {
                    return new ResourceDeltaAction(Resources.PLANT);
                }
            };
        }
        return null;
    }
}
