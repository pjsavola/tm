package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Mine extends Card {

    public Mine() {
        super("Mine", 4, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.STEEL;
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 steel income");
    }
}
