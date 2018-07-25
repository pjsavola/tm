package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.effect.ValueEffect;

public class AdvancedAlloys extends Card implements ValueEffect {

    public AdvancedAlloys() {
        super("Advanced Alloys", 9, Tags.SCIENCE, true);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Steel value +1", "Titanium value +1");
    }

    @Override
    public int getSteelDelta() {
        return 1;
    }

    @Override
    public int getTitaniumDelta() {
        return 1;
    }
}
