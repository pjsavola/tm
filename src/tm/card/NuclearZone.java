package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.PlaceTileAction;

public class NuclearZone extends Card {

    public NuclearZone() {
        super("Nuclear Zone", 10, Tags.EARTH);
    }

    @Override
    public int getVPs() {
        return -2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new PlaceTileAction(Tile.Type.NUCLEAR_ZONE),
            new AddTemperatureAction(),
            new AddTemperatureAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Place nuclear zone tile", "2 temperature");
    }
}
