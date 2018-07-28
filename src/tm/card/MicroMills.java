package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class MicroMills extends Card {

    public MicroMills() {
        super("Micro-Mills", 3, Tags.EMPTY);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.HEAT;
    }
}
