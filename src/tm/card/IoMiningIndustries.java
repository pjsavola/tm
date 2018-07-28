package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.effect.ScoringEffect;

public class IoMiningIndustries extends Card implements ScoringEffect {

    public IoMiningIndustries() {
        super("Io Mining Industries", 41, Tags.SPACE_JOVIAN);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(2, 0, 2, 0, 0, 0);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 vp per jovian tag");
    }

    @Override
    public int getVPs(Player player) {
        return player.getTags().getCount(Tags.Type.JOVIAN);
    }
}
