package tm.card;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.MarkerDeltaAction;
import tm.effect.PlaceTileEffect;
import tm.effect.PlayCardEffect;

public class Pets extends CardWithMarkers implements PlaceTileEffect, PlayCardEffect {

    public Pets() {
        super("Pets", 10, Tags.ANIMAL.combine(Tags.EARTH), true);
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new MarkerDeltaAction(1, this);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "Add 1 marker when city is placed", "Each 2 markers is worth 1 VP", "Currently " + getMarkerCount() + " markers", "", "1 marker");
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (Tile.isCity(tile.getType())) {
            return new MarkerDeltaAction(1, this);
        }
        return null;
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card instanceof PhobosSpaceHaven) {
            return new MarkerDeltaAction(1, this);
        } else if (card instanceof GanymedeColony) {
            return new MarkerDeltaAction(1, this);
        }
        return null;
    }
}
