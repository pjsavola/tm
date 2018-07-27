package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class MedicalLab extends Card {

    public MedicalLab() {
        super("Medical Lab", 13, Tags.SCIENCE.combine(Tags.BUILDING));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        final int buildingCount = game.getCurrentPlayer().getTags().getCount(Tags.Type.BUILDING);
        return new IncomeDeltaAction(new Resources((buildingCount + 1) / 2));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money income for each 2 building tags", "(including this)");
    }
}
