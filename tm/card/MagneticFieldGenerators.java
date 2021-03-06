package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;

public class MagneticFieldGenerators extends Card {

    public MagneticFieldGenerators() {
        super("Magnetic Field Generators", 20, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 2, -4, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddTerraformingRatingAction(3);
    }
}
