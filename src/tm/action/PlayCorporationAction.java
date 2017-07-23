package tm.action;

import java.util.ArrayList;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class PlayCorporationAction implements Action {

    @Override
    public Completable begin(final Game game) {
        final List<Card> corporations = new ArrayList<>(2);
        corporations.add(game.drawCorporation());
        corporations.add(game.drawCorporation());
        return new SelectCardsCompletable(game, corporations, SelectCardsCompletable.Type.PLAY);
    }
}
