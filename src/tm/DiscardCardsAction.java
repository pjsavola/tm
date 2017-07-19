package tm;

public class DiscardCardsAction extends SelectCardsAction {

    public DiscardCardsAction(final Game game) {
        super(game.getCurrentPlayer().getCards(), game);
    }
}
