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

// Decrease any heat income by 1 is done from dummy player
public class ArcticAlgae extends Card {

    public ArcticAlgae() {
        super("Arctic Algae", 12, Tags.PLANT, true);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() <= -12 + tolerance * 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.PLANT);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -12C or colder");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant", "2 plants for each ocean");
    }
}
