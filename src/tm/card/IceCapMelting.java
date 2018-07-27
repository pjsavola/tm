package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;
import tm.requirement.TemperatureRequirement;

public class IceCapMelting extends Card {

    public IceCapMelting() {
        super("Ice Cap Melting", 5, Tags.EVENT, new TemperatureRequirement(2, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddWaterAction();
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires +2C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 ocean");
    }
}
