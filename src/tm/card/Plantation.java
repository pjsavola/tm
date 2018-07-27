package tm.card;

import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.PlaceTileAction;

public class Plantation extends Card {

    public Plantation() {
        super("Plantation", 15, Tags.PLANT);
    }

    @Override
    public boolean check(Player player) {
        return player.getTags().hasAll(Tags.Type.SCIENCE.createTags(2));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(new PlaceTileAction(Tile.Type.GREENERY), new AddOxygenAction());
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 2 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("Greenery tile (+ oxygen)");
    }
}
