package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddOxygenAction;

// Remove 2 plants is done from dummy player
public class MiningExpedition extends Card {

    public MiningExpedition() {
        super("Mining Expedition", 12, Tags.EVENT);
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.STEEL_2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddOxygenAction();
    }
}
