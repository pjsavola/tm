package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class PowerGrid extends Card {

    public PowerGrid() {
        super("Power Grid", 18, Tags.POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        final int energy = (int) game.getCurrentPlayer().getPlayedCards().stream().filter(card -> card.getTags().has(Tags.Type.POWER)).count();
        return new IncomeDeltaAction(new Resources(-1, 0, 0, 0, energy + 1, 0));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 energy income for each power tag");
    }
}
