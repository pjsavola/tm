package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class AsteroidMining extends Card {

    public AsteroidMining() {
        super("Asteroid Mining", 30, new Tags().space().jovian());
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction() {
        return new IncomeDeltaAction(new Resources(0, 0, 2, 0, 0, 0));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 titanium income");
    }
}
