package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.MarkerDeltaAction;
import tm.action.PlaceTileAction;
import tm.effect.PlayCardEffect;

public class EcologicalZone extends CardWithMarkers implements PlayCardEffect {

    public EcologicalZone() {
        super("Ecological Zone", 12, Tags.PLANT.combine(Tags.ANIMAL));
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 2;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.ECOLOGICAL_ZONE);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Place next to greenery tile");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "When you play card with", "plant or animal tag", "(including these 2)", "Place marker on this card", "1 vp for each 2 markers", "Currently " + getMarkerCount() + " markers");
    }

    @Override
    public Action cardPlayed(Card card) {
        int markers = 0;
        if (card.getTags().has(Tags.Type.ANIMAL)) {
            markers++;
        }
        if (card.getTags().has(Tags.Type.PLANT)) {
            markers++;
        }
        if (markers > 0) {
            return new MarkerDeltaAction(markers, this);
        }
        return null;
    }
}
