package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.OxygenRequirement;

public class Zeppelins extends Card {

    public Zeppelins() {
        super("Zeppelins", 13, Tags.EMPTY, new OxygenRequirement(5, true));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(game.getCityCount(true));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 5%");
    }
}
