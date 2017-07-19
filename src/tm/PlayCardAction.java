package tm;

import java.util.List;

public class PlayCardAction extends SelectCardsAction {

	private static final long serialVersionUID = 1L;

    public PlayCardAction(final List<Card> selection, final Game game) {
        super(game.getCurrentPlayer().getCards(), game);
    }
}
