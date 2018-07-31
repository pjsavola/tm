package tm.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.DrawCardsAction;
import tm.action.MarkerDeltaAction;
import tm.action.SelectActionAction;
import tm.completable.Completable;
import tm.effect.PlayCardEffect;

public class OlympusConference extends CardWithMarkers implements PlayCardEffect {

    public OlympusConference() {
        super("Olympus Conference", 10, Tags.SCIENCE_BUILDING.combine(Tags.EARTH), null, true);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "When you play science tag", "Add marker to this card", "or remove marker to draw a card", "(including this)", "Currently " + getMarkerCount() + " markers");
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        final int tagCount = card.getTags().getCount(Tags.Type.SCIENCE);
        if (tagCount > 0) {
            final int usedMarkersMax = getMarkerCount();
            return new Action() {
                @Override
                public Completable begin(Game game) {
                    final List<Action> actions = new ArrayList<>();
                    actions.add(new MarkerDeltaAction(tagCount, OlympusConference.this) {
                        @Override
                        public String getDescription() {
                            return "Olympus Conference: Gain " + tagCount + " markers";
                        }
                    });
                    for (int i = 1; i <= usedMarkersMax && i <= tagCount; i++) {
                        final int drawnCards = i;
                        actions.add(new ActionChain(
                            new MarkerDeltaAction(tagCount - 2 * drawnCards, OlympusConference.this),
                            new DrawCardsAction(drawnCards, false, false)
                        ) {
                            @Override
                            public String getDescription() {
                                return "Olympus Conference: Gain " + (tagCount - 2 * drawnCards) + " markers, draw " + drawnCards + " cards";
                            }
                        });
                    }
                    return new SelectActionAction.SelectActionCompletable<>(game, actions);
                }
            };
        }
        return null;
    }
}
