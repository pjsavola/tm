package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class Cartel extends Card {

    public Cartel() {
        super("Cartel", 8, Tags.EARTH);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.EARTH) + 1);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money income for each earth tag", "(including this)");
    }
}
