package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;

public class TechnologyDemonstration extends Card {

    public TechnologyDemonstration() {
        super("Technology Demonstration", 5, Tags.SCIENCE.combine(Tags.SPACE).combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new DrawCardsAction(2, false, false);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Draw 2 cards");
    }
}
