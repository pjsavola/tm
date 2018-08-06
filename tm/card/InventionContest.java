package tm.card;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;

public class InventionContest extends Card {

    public InventionContest() {
        super("Invention Contest", 2, Tags.SCIENCE.combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new DrawCardsAction(3, 1, 1, false, false);
    }
}
