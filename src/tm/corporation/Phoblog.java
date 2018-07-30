package tm.corporation;

import java.util.Collections;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.effect.ValueEffect;

public class Phoblog extends Corporation implements ValueEffect {

    public Phoblog() {
        super("Phoblog", Tags.SPACE);
    }

    @Override
    public Resources getResourceDelta(Game game) {
        return new Resources(23, 0, 10, 0, 0, 0);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Titanium value +1");
    }

    @Override
    public int getTitaniumDelta() {
        return 1;
    }
}
