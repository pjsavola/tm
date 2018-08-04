package tm.card;

import java.awt.Graphics;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;
import tm.effect.ScoringEffect;

public class GanymedeColony extends Card implements ScoringEffect {

    public GanymedeColony() {
        super("Ganymede Colony", 20, Tags.SPACE_JOVIAN.combine(Tags.CITY));
    }

    @Override
    public int getVPs(Player player) {
        return player.getTags().getCount(Tags.Type.JOVIAN);
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawString("1 vp for each", x, y + 12);
        g.drawString("jovian tag", x, y + 28);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY) {
            @Override
            public Completable begin(Game game) {
                return new InstantCompletable(game) {
                    @Override
                    public void complete() {
                    }
                    @Override
                    public void undo() {
                    }
                    @Override
                    public void redo() {
                    }
                };
            }
        };
    }
}
