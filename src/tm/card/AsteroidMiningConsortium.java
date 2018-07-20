package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

// Decrease any titanium income by 1 is done from dummy player
public class AsteroidMiningConsortium extends Card {

    public AsteroidMiningConsortium() {
        super("Asteroid Mining Cons.", 13, new Tags().jovian());
    }

    @Override
    public boolean check(Player player) {
        return player.getIncome().titanium > 0;
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction() {
        return new IncomeDeltaAction(new Resources(0, 0, 1, 0, 0, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires titanium income");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 titanium income");
    }
}
