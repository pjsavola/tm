package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Tags;

public class InterstellarColonyShip extends Card {

    public InterstellarColonyShip() {
        super("Interstellar Colony Ship", 24, Tags.SPACE.combine(Tags.EARTH).combine(Tags.EVENT));
    }

    @Override
    public boolean check(Player player) {
        return player.getTags().hasAll(Tags.Type.SCIENCE.createTags(5));
    }

    @Override
    public int getVPs() {
        return 4;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 5 science tags");
    }
}
