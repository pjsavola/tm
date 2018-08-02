package tm.action;

import java.awt.Graphics;
import java.awt.Point;

import tm.Game;
import tm.Renderer;
import tm.Tile;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class AddWaterAction implements Action {

    private final Tile.Type type;

    public AddWaterAction() {
        this(Tile.Type.WATER);
    }

    public AddWaterAction(Tile.Type type) {
        this.type = type;
    }

    @Override
    public boolean check(Game game) {
        return game.getPlanet().getWaterRemaining() > 0;
    }

    @Override
    public Completable begin(Game game) {
        return new InstantCompletable(game) {
            @Override
            public void complete() {
                game.getPlanet().adjustWaterRemaining(-1);
                game.getCurrentPlayer().adjustRating(1);
                game.getActionHandler().addPendingAction(new PlaceTileAction(type));
            }

            @Override
            public void undo() {
                game.getPlanet().adjustWaterRemaining(1);
                game.getCurrentPlayer().adjustRating(-1);
            }

            @Override
            public void redo() {
                game.getPlanet().adjustWaterRemaining(-1);
                game.getCurrentPlayer().adjustRating(1);
            }
        };
    }

    @Override
    public boolean isOptional() {
        return true;
    }

    @Override
    public Point render(Graphics g, int x, int y, Game game) {
        return Renderer.renderIcon(g, Tile.Type.WATER, x, y);
    }
}
