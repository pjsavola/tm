package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Cards;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class RadSuits extends Card {

    public RadSuits() {
        super("Rad-Suits", 6, Tags.EMPTY);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    public boolean check(Game game, int tolerance) {
        int cityCount = game.getCityCount();
        if (Cards.PHOBOS_SPACE_HAVEN.getOwner() != null) {
            cityCount++;
        }
        if (Cards.GANYMEDE_COLONY.getOwner() != null) {
            cityCount++;
        }
        return cityCount >= 2 - tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(Resources.MONEY);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2 or more cities");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 money income");
    }

}
