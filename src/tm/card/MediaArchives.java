package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class MediaArchives extends Card {

    public MediaArchives() {
        super("Media Archives", 8, Tags.EARTH);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.EVENT));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 money for each event tag");
    }
}
