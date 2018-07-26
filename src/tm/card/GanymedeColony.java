package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Tags;
import tm.effect.ScoringEffect;

public class GanymedeColony extends Card implements ScoringEffect {

    public GanymedeColony() {
        super("Ganymede Colony", 20, Tags.SPACE.combine(Tags.CITY).combine(Tags.JOVIAN));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 vp per jovian tag", "City to Ganymede");
    }

    @Override
    public int getVPs(Player player) {
        return player.getTags().getCount(Tags.Type.JOVIAN);
    }
}
