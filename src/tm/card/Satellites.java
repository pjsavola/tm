package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Satellites extends Card {

    public Satellites() {
        super("Satellites", 10, Tags.SPACE);
    }

    @Override
    public Action getInitialAction(Game game) {
        final int spaceCount = game.getCurrentPlayer().getTags().getCount(Tags.Type.SPACE);
        return new IncomeDeltaAction(new Resources(spaceCount + 1));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money income for each space tag", "(including this)");
    }
}
