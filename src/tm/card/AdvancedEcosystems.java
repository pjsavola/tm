package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Tags;

public class AdvancedEcosystems extends Card {

    private static final Tags tags = Tags.ANIMAL.combine(Tags.MICROBE).combine(Tags.PLANT);

    public AdvancedEcosystems() {
        super("Advanced Ecosystems", 11, tags);
    }

    @Override
    public boolean check(Player player) {
        return player.getTags().hasAll(tags);
    }

    @Override
    public int getVPs() {
        return 3;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires microbe, animal and plant tag");
    }
}
