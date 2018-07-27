package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;

public class InventionContest extends Card {

    public InventionContest() {
        super("Invention Contest", 2, Tags.SCIENCE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new DrawCardsAction(3, 1, 1, false, false);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Draw 3 cards, keep 1 of them");
    }
}
