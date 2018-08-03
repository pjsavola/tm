package tm.card;

import tm.Card;
import tm.Tags;
import tm.requirement.TagRequirement;

public class InterstellarColonyShip extends Card {

    public InterstellarColonyShip() {
        super("Interstellar Colony Ship", 24, Tags.SPACE_EARTH_EVENT, new TagRequirement(Tags.Type.SCIENCE.createTags(5)));
    }

    @Override
    public int getVPs() {
        return 4;
    }
}
