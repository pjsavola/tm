package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;

public class Research extends Card {

    public Research() {
        super("Research", 11, Tags.Type.SCIENCE.createTags(2));
    }

    @Override
    public int getVPs() {
        return 2;
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
