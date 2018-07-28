package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.CityRequirement;

public class RadSuits extends Card {

    public RadSuits() {
        super("Rad-Suits", 6, Tags.EMPTY, new CityRequirement(2));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.MONEY;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2 or more cities");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 money income");
    }

}
