package tm.card;

import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.requirement.TemperatureRequirement;

public class Archaebacteria extends Card {

    public Archaebacteria() {
        super("Archaebacteria", 6, Tags.MICROBE, new TemperatureRequirement(-18, false));
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.PLANT;
    }
}
