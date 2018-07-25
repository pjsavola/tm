package tm.card;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlaceTileEffect;
import tm.effect.PlayCardEffect;

public class RoverConstruction extends Card implements PlaceTileEffect, PlayCardEffect {

    public RoverConstruction() {
        super("Rover Construction", 8, Tags.BUILDING, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "2 money for each city");
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (Tile.isCity(tile.getType())) {
            return new ResourceDeltaAction(new Resources(2));
        }
        return null;
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card instanceof PhobosSpaceHaven) {
            return new ResourceDeltaAction(new Resources(2));
        }
        return null;
    }
}
