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

public class MagneticFieldDome extends Card {

    public MagneticFieldDome() {
        super("Magnetic Field Dome", 5, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(new Resources(0, 0, 0, 1, -2, 0)),
            new AddTerraformingRatingAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant income", "-2 energy income", "1 TR");
    }
}
