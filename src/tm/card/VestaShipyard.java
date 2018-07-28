package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;

public class VestaShipyard extends Card {

    public VestaShipyard() {
        super("Vesta Shipyard", 15, Tags.SPACE_JOVIAN);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.TITANIUM;
    }
}
