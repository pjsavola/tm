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

public class Zeppelins extends Card {

    public Zeppelins() {
        super("Zeppelins", 13, Tags.EMPTY);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 5 - tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(game.getCityCount()));
    }


    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 5%");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 money income for each city");
    }
}
