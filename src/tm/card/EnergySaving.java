package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;

public class EnergySaving extends Card {

    public EnergySaving() {
        super("Energy Saving", 15, Tags.POWER);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources(0, 0, 0, 0, game.getCityCount(false), 0));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 energy income for each city");
    }
}
