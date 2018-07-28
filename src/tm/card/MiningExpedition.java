package tm.card;

import java.util.Arrays;
import java.util.List;

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
    public Resources getIncomeDelta(Game game) {
        return Resources.STEEL_2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddOxygenAction();
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 steel", "1 oxygen");
    }
}
