package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Sponsors extends Card {

    public Sponsors() {
        super("Sponsors", 6, Tags.EARTH);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(2);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 money income");
    }
}
