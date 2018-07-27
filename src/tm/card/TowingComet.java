package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.AddWaterAction;
import tm.action.ResourceDeltaAction;

public class TowingComet extends Card {

    public TowingComet() {
        super("Towing Comet", 23, Tags.SPACE_EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(Resources.PLANT_2),
            new AddOxygenAction(),
            new AddWaterAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 plants", "1 oxygen", "1 water");
    }
}
