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

public class RadChemFactory extends Card {

    public RadChemFactory() {
        super("Rad-Chem Factory", 8, Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new IncomeDeltaAction(Resources.ENERGY.negate()),
            new AddTerraformingRatingAction(2)
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("-1 energy income", "2 TR");
    }
}
