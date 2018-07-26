package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;

public class BribedCommittee extends Card {

    public BribedCommittee() {
        super("Bribed Committee", 7, Tags.EARTH.combine(Tags.EVENT));
    }

    @Override
    public int getVPs() {
        return -2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddTerraformingRatingAction(2);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 TR");
    }
}
