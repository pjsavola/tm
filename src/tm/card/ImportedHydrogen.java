package tm.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddWaterAction;
import tm.action.CardWithMarkers;
import tm.action.ResourceDeltaAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;
import tm.completable.SelectCardsCompletable;

public class ImportedHydrogen extends Card {

    public ImportedHydrogen() {
        super("Imported Hydrogen", 16, new Tags().space().earth().event());
    }

    @Override
    public Action getInitialAction() {
        return new ActionChain(
            new Action() {
                @Override
                public Completable begin(Game game) {
                    final List<CardWithMarkers> cards = game
                        .getCurrentPlayer()
                        .getPlayedCards()
                        .stream()
                        .filter(card -> card instanceof CardWithMarkers)
                        .map(card -> (CardWithMarkers) card)
                        .filter(card -> card.getTags().hasMicrobe() || card.getTags().hasAnimal())
                        .collect(Collectors.toList());
                    if (cards.isEmpty()) {
                        // Always take 3 plants
                        return new InstantCompletable(game) {
                            @Override
                            public void complete() {
                                game.getActionHandler().addPendingAction(new ResourceDeltaAction(new Resources(0, 0, 0, 3, 0, 0)));
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
                        private int markers;

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
                            return "Select microbe or animal card, or nothing to get 3 plants";
                        }

                        @Override
                        public void complete() {
                            if (!selectedCards.isEmpty()) {
                                selectedCard = (CardWithMarkers) selectedCards.iterator().next();
                            }
                            if (selectedCard == null) {
                                game.getActionHandler().addPendingAction(new ResourceDeltaAction(new Resources(0, 0, 0, 3, 0, 0)));
                            } else {
                                markers = selectedCard.getTags().hasMicrobe() ? 3 : 2;
                                selectedCard.adjustMarkers(markers);
                            }
                            cancel();
                        }

                        @Override
                        public void undo() {
                            if (selectedCard != null) {
                                selectedCard.adjustMarkers(-markers);
                            }
                        }

                        @Override
                        public void redo() {
                            if (selectedCard != null) {
                                selectedCard.adjustMarkers(markers);
                            }
                        }
                    };
                }
            },
            new AddWaterAction()
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 plants /", "3 microbes /", "2 animals", "1 water");
    }
}
