package tm.corporation;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.ResourceDeltaAction;
import tm.card.GanymedeColony;
import tm.card.PhobosSpaceHaven;
import tm.effect.PlaceTileEffect;
import tm.effect.PlayCardEffect;

public class TharsisRepublic extends Corporation implements PlaceTileEffect, PlayCardEffect {

    public TharsisRepublic() {
        super("Tharsis Republic", Tags.BUILDING);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new ResourceDeltaAction(new Resources(40)),
            new IncomeDeltaAction(new Resources(2)), // Initial cities in solo game
            new PlaceTileAction(Tile.Type.CITY));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("40 money", "3 money for each city you play", "1 money income for each city");
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (Tile.isCity(tile.getType())) {
            return new ActionChain(
                new ResourceDeltaAction(new Resources(3)),
                new IncomeDeltaAction(Resources.MONEY)
            );
        }
        return null;
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card instanceof PhobosSpaceHaven) {
            return new ResourceDeltaAction(new Resources(3));
        } else if (card instanceof GanymedeColony) {
            return new ResourceDeltaAction(new Resources(3));
        }
        return null;
    }
}
