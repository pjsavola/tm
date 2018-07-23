package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Planet;
import tm.Tags;

public class ColonizerTrainingCamp extends Card {

    public ColonizerTrainingCamp() {
        super("Colonizer Training Camp", 8, Tags.BUILDING.combine(Tags.JOVIAN));
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() <= 5 + tolerance;
    }

    @Override
    public int getVPs() {
        return 2;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 5% or less");
    }
}
