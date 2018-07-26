package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Planet;
import tm.Tags;

public class DustSeals extends Card {

    public DustSeals() {
        super("Dust Seals", 2, Tags.EMPTY);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getWaterPlaced() <= 3 + tolerance;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires at most 3 ocean tiles");
    }
}
