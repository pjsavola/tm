package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddWaterAction;

public class IceAsteroid extends Card {

    public IceAsteroid() {
        super("Ice Asteroid", 23, Tags.SPACE.combine(Tags.EVENT));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddWaterAction(),
            new AddWaterAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 oceans");
    }
}
