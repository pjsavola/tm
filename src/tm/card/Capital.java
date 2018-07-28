package tm.card;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.ImageCache;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.effect.ScoringEffect;
import tm.requirement.OceanRequirement;

public class Capital extends Card implements ScoringEffect {

    public Capital() {
        super("Capital", 26, Tags.BUILDING_CITY, new OceanRequirement(4, true));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(5, 0, 0, 0, -2, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CAPITAL);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4 ocean tiles");
    }

    @Override
    public int getVPs(Player player) {
        final Tile tile = player.findTile(Tile.Type.CAPITAL);
        if (tile != null) {
            return (int) tile.getNeighbors().stream().filter(t -> t.getType() == Tile.Type.WATER).count();
        } else {
            return 0;
        }
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/icon_water.png"), x, y, null);
    }
}
