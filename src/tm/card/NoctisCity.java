package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class NoctisCity extends Card {

    public NoctisCity() {
        super("Noctis City", 18, Tags.BUILDING.combine(Tags.CITY));
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() <= 7 + tolerance;
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new Action() {
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
            },
            new IncomeDeltaAction(new Resources(3, 0, 0, 0, -1, 0))
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 money income", "-1 energy income");
    }
}
