package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.DrawCardsAction;

public class BusinessContracts extends Card {

    public BusinessContracts() {
        super("Business Contracts", 7, Tags.EARTH.combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new DrawCardsAction(4, 2, 2, false, false);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Draw 4 cards", "Keep 2 of them");
    }
}
