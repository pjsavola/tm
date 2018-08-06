package tm.card;

import tm.Card;
import tm.Tags;

public class TransNeptuneProbe extends Card {

    public TransNeptuneProbe() {
        super("Trans-Neptune Probe", 6, Tags.SPACE.combine(Tags.SCIENCE));
    }

    @Override
    public int getVPs() {
        return 1;
    }
}
