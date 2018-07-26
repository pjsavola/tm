package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;

public class Worms extends Card {

    public Worms() {
        super("Worms", 8, Tags.MICROBE);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() >= 4 - tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        final int microbeCount = (int) game.getCurrentPlayer().getPlayedCards().stream().filter(card -> card.getTags().has(Tags.Type.MICROBE)).count();
        return new IncomeDeltaAction(new Resources(0, 0, 0,  (microbeCount + 1) / 2, 0, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be at least 4%");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant income for each 2 microbe tags", "(including this)");
    }
}
