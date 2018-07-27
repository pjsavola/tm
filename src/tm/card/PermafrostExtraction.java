package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;
import tm.requirement.TemperatureRequirement;

public class PermafrostExtraction extends Card {

    public PermafrostExtraction() {
        super("Permafrost Extraction", 8, Tags.EVENT, new TemperatureRequirement(-8, true));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddWaterAction();
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires -8C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 ocean");
    }
}
