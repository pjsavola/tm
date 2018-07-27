package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Cartel extends Card {

    public Cartel() {
        super("Cartel", 8, Tags.EARTH);
    }

    @Override
    public Action getInitialAction(Game game) {
        final int earthCount = game.getCurrentPlayer().getTags().getCount(Tags.Type.EARTH);
        return new IncomeDeltaAction(new Resources(earthCount + 1));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money income for each earth tag", "(including this)");
    }
}
