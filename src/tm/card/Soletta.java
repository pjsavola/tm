package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Soletta extends Card {

    public Soletta() {
        super("Soletta", 35, Tags.SPACE);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, 0, 7);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("7 titanium income");
    }
}
