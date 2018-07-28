package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.AddTerraformingRatingAction;

public class MagneticFieldDome extends Card {

    public MagneticFieldDome() {
        super("Magnetic Field Dome", 5, Tags.BUILDING);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 1, -2, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddTerraformingRatingAction();
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant income", "-2 energy income", "1 TR");
    }
}
