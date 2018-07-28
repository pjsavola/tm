package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class MedicalLab extends Card {

    public MedicalLab() {
        super("Medical Lab", 13, Tags.SCIENCE_BUILDING);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources((game.getCurrentPlayer().getTags().getCount(Tags.Type.BUILDING) + 1) / 2);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 money income for each 2 building tags", "(including this)");
    }
}
