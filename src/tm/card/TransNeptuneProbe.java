package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

// What does this do?
public class TransNeptuneProbe extends Card {

    public TransNeptuneProbe() {
        super("Trans-Neptune Probe", 6, Tags.SPACE.combine(Tags.SCIENCE));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(Resources.EMPTY);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("");
    }
}
