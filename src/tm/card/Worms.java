package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.OxygenRequirement;

public class Worms extends Card {

    public Worms() {
        super("Worms", 8, Tags.MICROBE, new OxygenRequirement(4, true));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, (game.getCurrentPlayer().getTags().getCount(Tags.Type.MICROBE) + 1) / 2, 0, 0);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 4%");
    }
}
