package tm.effect;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.action.Action;

public interface PlayCardEffect {
    @Nullable
    Action cardPlayed(Card card);
}