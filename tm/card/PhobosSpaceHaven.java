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

public class PhobosSpaceHaven extends Card {

    public PhobosSpaceHaven() {
        super("Phobos Space Haven", 25, Tags.SPACE.combine(Tags.CITY));
    }

    @Override
    public int getVPs() {
        return 3;
    }

    @Override
    public Resources getIncomeDelta() {
        return Resources.TITANIUM;
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
