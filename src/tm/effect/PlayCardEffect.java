package tm.effect;

import tm.Card;
import tm.Game;

public interface PlayCardEffect {
    void cardPlayed(Game game, Card card);
}
