package tm.card;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;

public class TechnologyDemonstration extends Card {

    public TechnologyDemonstration() {
        super("Technology Demonstration", 5, Tags.SCIENCE.combine(Tags.SPACE_EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new DrawCardsAction(2, false, false);
    }
}
