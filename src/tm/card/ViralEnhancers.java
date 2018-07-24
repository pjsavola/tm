package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;

public class ViralEnhancers extends Card {

    public ViralEnhancers() {
        super("Viral Enhancers", 8, Tags.SCIENCE.combine(Tags.MICROBE), true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "When playing card with animal/plant/microbe tag", "(including this)", "add marker to that card or gain 1 plant");
    }
}
