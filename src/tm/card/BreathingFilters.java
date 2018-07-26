package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Planet;
import tm.Tags;

public class BreathingFilters extends Card {

    public BreathingFilters() {
        super("Breathing Filters", 11, Tags.SCIENCE);
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 7 - tolerance;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 7%");
    }
}
