package tm.completable;

import tm.Game;

public class NopCompletable extends InstantCompletable {

    public NopCompletable(Game game) {
        super(game);
    }

    @Override
    public void complete() {
    }

    @Override
    public void undo() {
    }

    @Override
    public void redo() {
    }
}
