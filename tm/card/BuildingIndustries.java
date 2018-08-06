package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class BuildingIndustries extends Card {

    public BuildingIndustries() {
        super("Building Industries", 6, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 2, 0, 0, -1, 0);
    }
}
