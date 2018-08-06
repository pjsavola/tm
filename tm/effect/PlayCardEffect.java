package tm.effect;

import javax.annotation.Nullable;
import tm.Card;
import tm.action.Action;

public interface PlayCardEffect {
    @Nullable
    Action cardPlayed(Card card);
}
