package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Tags;

public class StandardTechnology extends Card {

    public StandardTechnology() {
        super("Standard Technology", 6, Tags.SCIENCE, true);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Effect: Get 3 money after standard project");
    }
}
