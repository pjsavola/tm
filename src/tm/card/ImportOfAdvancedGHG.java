package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class ImportOfAdvancedGHG extends Card {

    public ImportOfAdvancedGHG() {
        super("Import of Advanced GHG", 9, Tags.SPACE_EARTH_EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 0, 2));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 heat income");
    }
}
