package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class CallistoPenalMines extends Card {

    public CallistoPenalMines() {
        super("Callisto Penal Mines", 24, Tags.SPACE_JOVIAN);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(3);
    }
}
