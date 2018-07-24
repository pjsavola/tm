package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;

public class MarsUniversity extends Card {

    public MarsUniversity() {
        super("Mars University", 8, Tags.SCIENCE.combine(Tags.BUILDING), true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "When playing card with science tag", "(including this)", "you may discard a card to draw a card");
    }
}
