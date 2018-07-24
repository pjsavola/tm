package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;

public class AdvancedAlloys extends Card {

    public AdvancedAlloys() {
        super("Advanced Alloys", 9, Tags.SCIENCE, true);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Steel value +1", "Titanium value +1");
    }
}
