package tm.card;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.PlaceTileAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class NoctisCity extends Card {

    public NoctisCity() {
        super("Noctis City", 18, Tags.BUILDING_CITY);
    }

    @Override
    public Resources getIncomeDelta() {
        return new Resources(3, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY) {
            @Override
            public Completable begin(Game game) {
                final Tile noctis = game.getNoctisTile();
                return new InstantCompletable(game) {
                    @Override
                    public void complete() {
                        PlaceTileAction.placeTile(game, noctis, Tile.Type.CITY);
                        game.repaint();
                    }

                    @Override
                    public void undo() {
                        noctis.setType(null);
                        noctis.setOwner(null);
                        game.repaint();
                    }

                    @Override
                    public void redo() {
                        noctis.setType(Tile.Type.CITY);
                        noctis.setOwner(game.getCurrentPlayer());
                        game.repaint();
                    }
                };
            }
        };
    }
}
