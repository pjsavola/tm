package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class ImportOfAdvancedGHG extends Card {

    public ImportOfAdvancedGHG() {
        super("Import of Advanced GHG", 9, Tags.SPACE_EARTH_EVENT);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 0, 2);
    }
}
