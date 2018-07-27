package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Tags;
import tm.action.Action;
import tm.action.AddWaterAction;

public class IceCapMelting extends Card {

    public IceCapMelting() {
        super("Ice Cap Melting", 5, Tags.EVENT);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getTemperature() >= 2 - 2 * tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new AddWaterAction();
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires +2C or warmer");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 ocean");
    }
}
