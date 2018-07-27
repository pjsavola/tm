package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.requirement.TagRequirement;

public class LightningHarvest extends Card {

    public LightningHarvest() {
        super("LightningHarvest", 8, Tags.POWER, new TagRequirement(Tags.SCIENCE_3));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(1, 0, 0, 0, 1, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money income", "1 energy income");
    }
}
