package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class PhobosSpaceHaven extends Card {

    public PhobosSpaceHaven() {
        super("Phobos Space Haven", 25, Tags.SPACE.combine(Tags.CITY));
    }

    @Override
    public int getVPs() {
        return 3;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
    }
}
