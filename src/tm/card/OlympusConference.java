package tm.card;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.ImageCache;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.CardAction;
import tm.action.DrawCardsAction;
import tm.action.MarkerDeltaAction;
import tm.action.SelectActionAction;
import tm.completable.Completable;
import tm.effect.PlayCardEffect;

public class OlympusConference extends CardWithMarkers implements PlayCardEffect {

    public OlympusConference() {
        super("Olympus Conference", 10, Tags.SCIENCE_BUILDING.combine(Tags.EARTH), null, true, 0, 1);
    }

    @Override
    public int getVPs() {
        return 1;
    }

    @Override
    protected void renderEffect(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_science.png"), x, y + 16, null);
        g.drawString("1 marker OR", x + 24, y + 12);
        g.drawString("-1 marker", x + 24, y + 28);
        g.drawString("to draw 1", x + 24, y + 44);
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
                    final List<CardAction> actions = new ArrayList<>();
                    actions.add(new CardAction(true, null) {
                        @Override
                        public String getDescription() {
                            return "Olympus Conference: Gain " + tagCount + " markers";
                        }
                        @Override
                        public Action getAction(Game game) {
                            return new MarkerDeltaAction(tagCount, OlympusConference.this);
                        }
                    });
                    for (int i = 1; i <= usedMarkersMax && i <= tagCount; i++) {
                        final int drawnCards = i;
                        actions.add(new CardAction(false, null) {
                            @Override
                            public String getDescription() {
                                return "Olympus Conference: Gain " + (tagCount - 2 * drawnCards) + " markers, draw " + drawnCards + " cards";
                            }
                            @Override
                            public Action getAction(Game game) {
                                return new ActionChain(
                                    new MarkerDeltaAction(tagCount - 2 * drawnCards, OlympusConference.this),
                                    new DrawCardsAction(drawnCards, false, false)
                                );
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
