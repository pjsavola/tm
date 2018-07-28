package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class BuildingIndustries extends Card {

    public BuildingIndustries() {
        super("Building Industries", 6, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 2, 0, 0, -1, 0);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 steel income", "-1 energy income");
    }
}
