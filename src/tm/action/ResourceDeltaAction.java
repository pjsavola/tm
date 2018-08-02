package tm.action;

import java.awt.Graphics;
import java.awt.Point;

import com.sun.istack.internal.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class ResourceDeltaAction implements Action {

    @Nullable
    final Resources delta;

    public ResourceDeltaAction(Resources delta) {
        this.delta = delta;
    }

    @Nullable
    public Resources getDelta(Game game) {
        return delta;
    }

    @Override
    public boolean check(Game game) {
        return game.getCurrentPlayer().canAdjustResources(getDelta(game));
    }

    @Override
    public Completable begin(Game game) {
        final Player targetPlayer = game.getCurrentPlayer();
        final Resources delta = getDelta(game);
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                targetPlayer.adjustResources(delta);
            }

            @Override
            public void undo() {
                targetPlayer.adjustResources(delta.negate());
            }

            @Override
            public void redo() {
                targetPlayer.adjustResources(delta);
            }
        };
    }

    @Override
    public Point render(Graphics g, int x, int y, Game game) {
        return delta.render(g, x, y, false);
    }
}
