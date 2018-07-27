package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Tags;
import tm.effect.ScoringEffect;

public class IoMiningIndustries extends Card implements ScoringEffect {

    public IoMiningIndustries() {
        super("Io Mining Industries", 41, Tags.SPACE_JOVIAN);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("2 money income", "2 titanium income", "1 vp per jovian tag");
    }

    @Override
    public int getVPs(Player player) {
        return player.getTags().getCount(Tags.Type.JOVIAN);
    }
}
