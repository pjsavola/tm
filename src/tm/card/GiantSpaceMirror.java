package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class GiantSpaceMirror extends Card {

    public GiantSpaceMirror() {
        super("Giant Space Mirror", 17, Tags.SPACE.combine(Tags.POWER));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 3, 0));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("3 energy income");
    }
}
