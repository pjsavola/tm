package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.ResourceDeltaAction;

public class ImportedGHG extends Card {

    public ImportedGHG() {
        super("Imported GHG", 7, Tags.SPACE_EARTH_EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(Resources.HEAT),
            new ResourceDeltaAction(new Resources(0, 0, 0, 0, 0, 3))
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 heat", "1 heat income");
    }
}
