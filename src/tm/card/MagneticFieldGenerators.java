package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTerraformingRatingAction;
import tm.action.IncomeDeltaAction;

public class MagneticFieldGenerators extends Card {

    public MagneticFieldGenerators() {
        super("Magnetic Field Generators", 20, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(new Resources(0, 0, 0, 2, -4, 0)),
            new AddTerraformingRatingAction(3)
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 plan income", "-4 energy income", "3 TR");
    }
}
