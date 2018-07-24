package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Tags;

public class EarthCatapult extends Card {

    public EarthCatapult() {
        super("Earth Catapult", 23, Tags.EARTH, true);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 money discount for all cards");
    }
}
