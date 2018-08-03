package tm.card;

import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ResourceDeltaAction;
import tm.effect.PlaceTileEffect;
import tm.requirement.TemperatureRequirement;

public class ArcticAlgae extends Card implements PlaceTileEffect {

    public ArcticAlgae() {
        super("Arctic Algae", 12, Tags.PLANT, new TemperatureRequirement(-12, false), true);
    }

    @Override
    public Resources getResourceDelta() {
        return Resources.PLANT;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(Tile.Type.WATER.getIcon(), x, y, null);
        Resources.PLANT_2.render(g, x + 30, y + 4, false);
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (tile.getType() == Tile.Type.WATER) {
            return new ResourceDeltaAction(Resources.PLANT_2);
        }
        return null;
    }
}
