package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class MirandaResort extends Card {

    public MirandaResort() {
        super("Miranda Resort", 12, Tags.SPACE.combine(Tags.JOVIAN));
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(game.getCurrentPlayer().getTags().getCount(Tags.Type.EARTH)));
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 money income for each Earth tag");
    }
}
