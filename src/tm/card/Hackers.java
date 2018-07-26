package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

// Removes 2 money income from dummy opponent
public class Hackers extends Card {

    public Hackers() {
        super("Hackers", 3, Tags.EMPTY);
    }

    @Override
    public int getVPs() {
        return -1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(2, 0, 0, 0, -1, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 money income", "-1 energy income");
    }
}
