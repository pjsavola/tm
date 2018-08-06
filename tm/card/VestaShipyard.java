package tm.card;

import tm.Card;
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
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
    }
}
