package tm.card;

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
}
