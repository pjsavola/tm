package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class ImportedGHG extends Card {

    public ImportedGHG() {
        super("Imported GHG", 7, Tags.SPACE_EARTH_EVENT);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(0, 0, 0, 0, 0, 3);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.HEAT;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 heat", "1 heat income");
    }
}
