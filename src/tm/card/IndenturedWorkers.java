package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.PlayCardAction;

public class IndenturedWorkers extends Card {

    public IndenturedWorkers() {
        super("Indentured Workers", 0, Tags.EVENT);
    }

    @Override
    public int getVPs() {
        return -1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlayCardAction(8, 0);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Next card costs 8 money less");
    }
}
