package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Tags;

public class BeamFromThoriumAsteroid extends Card {

    public BeamFromThoriumAsteroid() {
        super("Beam From Thorium Asteroid", 32, new Tags().space().jovian().power());
    }

    @Override
    public boolean check(Player player) {
        return player.hasTags(new Tags().jovian());
    }

    @Override
    public int getVPs() {
        return 1;
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
