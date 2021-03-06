package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;

public class GiantSpaceMirror extends Card {

    public GiantSpaceMirror() {
        super("Giant Space Mirror", 17, Tags.SPACE.combine(Tags.POWER));
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(0, 0, 0, 0, 3, 0);
    }
}
