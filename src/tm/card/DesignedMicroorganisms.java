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

public class DesignedMicroorganisms extends Card {

    public DesignedMicroorganisms() {
        super("Designed Microorganisms", 16, Tags.SCIENCE.combine(Tags.MICROBE));
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() <= -14 + tolerance * 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.PLANT_2);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -14C or colder");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("2 plant income");
    }
}
