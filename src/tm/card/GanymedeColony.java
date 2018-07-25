package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Tags;
import tm.effect.JovianEffect;

public class GanymedeColony extends Card implements JovianEffect {

    public GanymedeColony() {
        super("Ganymede Colony", 20, Tags.SPACE.combine(Tags.CITY).combine(Tags.JOVIAN));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 vp per jovian tag", "City to Ganymede");
    }
}
