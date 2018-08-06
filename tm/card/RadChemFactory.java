package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;

public class RadChemFactory extends Card {

    public RadChemFactory() {
        super("Rad-Chem Factory", 8, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.ENERGY.negate();
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddTerraformingRatingAction(2);
    }
}
