package tm.action;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.completable.Completable;
import tm.completable.InstantCompletable;
import tm.completable.SelectCardsCompletable;

public class AddMarkerAction implements Action {

    private final List<CardWithMarkers> cards;

    public AddMarkerAction(List<Card> cards) {
        this.cards = filter(cards
            .stream()
            .filter(card -> card instanceof CardWithMarkers)
            .map(card -> (CardWithMarkers) card)
        ).collect(Collectors.toList());
    }

    protected String getTitle() {
        return "Add markers to card...";
    }

    protected Stream<CardWithMarkers> filter(Stream<CardWithMarkers> stream) {
        return stream;
    }

    @Nullable
    protected Action onEmptySelection() {
        return null;
    }

    protected int getMarkerCount(CardWithMarkers card) {
        return 1;
    }

    @Override
    public Completable begin(Game game) {
        if (cards.isEmpty()) {
            return new InstantCompletable(game) {
                @Override
                public void complete() {
                    final Action action = onEmptySelection();
                    if (action != null) {
                        game.getActionHandler().addPendingAction(onEmptySelection());
                    }
                }

                @Override
                public void undo() {
                }

                @Override
                public void redo() {
                }
            };
        }
        return new SelectCardsCompletable(game, cards) {
            @Nullable
            private CardWithMarkers selectedCard;

            @Override
            public boolean check() {
                return true;
            }

            @Override
            public int maxSelection() {
                return 1;
            }

            @Override
            public String getTitle() {
                return AddMarkerAction.this.getTitle();
            }

            @Override
            public void complete() {
                if (!selectedCards.isEmpty()) {
                    selectedCard = (CardWithMarkers) selectedCards.iterator().next();
                }
                if (selectedCard == null) {
                    final Action action = onEmptySelection();
                    if (action != null) {
                        game.getActionHandler().addPendingAction(onEmptySelection());
                    }
                } else {
                    selectedCard.adjustMarkers(getMarkerCount(selectedCard));
                }
                cancel();
            }

            @Override
            public void undo() {
                if (selectedCard != null) {
                    selectedCard.adjustMarkers(-getMarkerCount(selectedCard));
                }
            }

            @Override
            public void redo() {
                if (selectedCard != null) {
                    selectedCard.adjustMarkers(getMarkerCount(selectedCard));
                }
            }
        };
    }
}
