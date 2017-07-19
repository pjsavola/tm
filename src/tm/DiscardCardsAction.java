package tm;

public class DiscardCardsAction extends SelectCardsAction {

	private static final long serialVersionUID = 1L;

    public DiscardCardsAction(final Game game) {
        super(game.getCurrentPlayer().getCards(), game);
    }
}
