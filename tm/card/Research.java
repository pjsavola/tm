package tm.card;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;

public class Research extends Card {

    public Research() {
        super("Research", 11, Tags.SCIENCE_2);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new DrawCardsAction(2, false, false);
    }
}
