package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.requirement.TagRequirement;

public class GeneRepair extends Card {

    public GeneRepair() {
        super("Gene Repair", 12, Tags.SCIENCE, new TagRequirement(Tags.Type.SCIENCE.createTags(3)));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(2));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 3 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 money income");
    }
}
