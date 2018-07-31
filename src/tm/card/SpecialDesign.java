package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.PlayCardAction;

public class SpecialDesign extends Card {

    public SpecialDesign() {
        super("Special Design", 4, Tags.SCIENCE.combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlayCardAction(game.getCurrentPlayer().getCards(), "Play card with Special Design", 0, 2);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Next card has +/- 2 requirements");
    }
}
