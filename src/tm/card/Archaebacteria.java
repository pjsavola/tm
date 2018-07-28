package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Archaebacteria extends Card {

    public Archaebacteria() {
        super("Archaebacteria", 6, Tags.MICROBE, new TemperatureRequirement(-18, false));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return Resources.PLANT;
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("It must be -18C or colder");
    }
}
