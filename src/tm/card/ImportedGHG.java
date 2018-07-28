package tm.card;

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
}
