package tm.card;

import java.awt.Graphics;

import javax.annotation.Nullable;
import tm.CardWithMarkers;
import tm.Game;
import tm.ImageCache;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.MarkerDeltaAction;
import tm.effect.PlaceTileEffect;
import tm.requirement.OxygenRequirement;

// Removing 1 plant income is done from dummy player
public class Herbivores extends CardWithMarkers implements PlaceTileEffect {

    public Herbivores() {
        super("Herbivores", 12, Tags.ANIMAL, new OxygenRequirement(8, true), true);
    }

    @Override
    public int getVPs() {
        return getMarkerCount() / 2;
    }

    @Override
    public String getRatio() {
        return "1:2";
    }

    @Override
    public Action getInitialAction(Game game) {
        return new MarkerDeltaAction(1, this);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/icon_greenery.png"), x, y, null);
        g.drawString("1 marker", x + 30, y + 16);
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (tile.isGreenery()) {
            return new MarkerDeltaAction(1, this);
        }
        return null;
    }
}
