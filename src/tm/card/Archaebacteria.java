package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Archaebacteria extends Card {

    public Archaebacteria() {
        super("Archaebacteria", 6, new Tags().microbe());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() <= -18 + tolerance * 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 1, 0, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -18C or colder");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 plant income");
    }
}
