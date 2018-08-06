package tm.card;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;

public class LagrangeObservatory extends Card {

    public LagrangeObservatory() {
        super("Lagrange Observatory", 9, Tags.SCIENCE.combine(Tags.SPACE));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new DrawCardsAction(1, false, false);
    }
}
