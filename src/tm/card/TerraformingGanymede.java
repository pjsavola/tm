package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;

public class TerraformingGanymede extends Card {

    public TerraformingGanymede() {
        super("Terraforming Ganymede", 33, Tags.SPACE_JOVIAN);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        final int jovianCount = game.getCurrentPlayer().getTags().getCount(Tags.Type.JOVIAN);
        return new AddTerraformingRatingAction(jovianCount + 1);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 TR for each jovian tag", "(including this)");
    }
}
