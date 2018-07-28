package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.TagRequirement;

public class BeamFromThoriumAsteroid extends Card {

    public BeamFromThoriumAsteroid() {
        super("Beam From Thorium Asteroid", 32, Tags.SPACE_JOVIAN.combine(Tags.POWER), new TagRequirement(Tags.JOVIAN));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(0, 0, 0, 0, 3, 3);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires jovian tag");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 energy income", "3 heat income");
    }
}
