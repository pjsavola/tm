package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddWaterAction;
import tm.requirement.TemperatureRequirement;

// Decrease any 3 plants is done from dummy player
public class LakeMarineris extends Card {

    public LakeMarineris() {
        super("Lake Marineris", 18, Tags.EMPTY, new TemperatureRequirement(0, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(new AddWaterAction(), new AddWaterAction());
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 0C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 water");
    }
}
