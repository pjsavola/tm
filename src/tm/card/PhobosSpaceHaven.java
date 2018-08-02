package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class PhobosSpaceHaven extends Card {

    public PhobosSpaceHaven() {
        super("Phobos Space Haven", 25, Tags.SPACE.combine(Tags.CITY));
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("City to Phobos");
    }
}
