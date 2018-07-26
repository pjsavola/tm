package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class MediaArchives extends Card {

    public MediaArchives() {
        super("Media Archives", 8, Tags.EARTH);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources((int) game.getCurrentPlayer().getPlayedCards().stream().filter(card -> card.getTags().has(Tags.Type.EVENT)).count()));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 money for each event tag");
    }
}
