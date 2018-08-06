package tm.card;

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
}
