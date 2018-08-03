package tm.card;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.PlaceTileAction;
import tm.requirement.TagRequirement;

public class Plantation extends Card {

    public Plantation() {
        super("Plantation", 15, Tags.PLANT, new TagRequirement(Tags.SCIENCE_2));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(new PlaceTileAction(Tile.Type.GREENERY), new AddOxygenAction());
    }
}
