package tm.corporation;

import java.util.Arrays;
import java.util.List;

import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.ValueEffect;

public class Phoblog extends Corporation implements ValueEffect {

    public Phoblog() {
        super("Phoblog", Tags.SPACE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ResourceDeltaAction(new Resources(23, 0, 10, 0, 0, 0));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("23 money", "10 titanium", "Titanium value +1");
    }

    @Override
    public int getTitaniumDelta() {
        return 1;
    }
}
