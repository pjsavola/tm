package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddWaterAction;
import tm.action.DrawCardsAction;

public class ConvoyFromEuropa extends Card {

    public ConvoyFromEuropa() {
        super("Convoy From Europa", 15, Tags.SPACE_EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddWaterAction(),
            new DrawCardsAction(1, false, false)
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 ocean", "1 card");
    }
}
