package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class MethaneFromTitan extends Card {

    public MethaneFromTitan() {
        super("Methane From Titan", 28, new Tags().space().jovian());
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 2 - tolerance;
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 2, 0, 2));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2% oxygen");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 plant income", "2 heat income");
    }
}
