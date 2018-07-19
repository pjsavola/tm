package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.ResourceDeltaAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;
import tm.completable.SelectCardsCompletable;

public class SearchForLife extends Card {

    private int generationLimit;
    private int markerCount;

    public SearchForLife() {
        super("Search For Life", 3, new Tags().science(), false);
    }

    @Override
    public boolean check(Planet planet, int tolerance) {
        return planet.getOxygen() <= 6 + tolerance;
    }

    @Override
    public int getVPs() {
        return markerCount > 0 ? 3 : 0;
    }

    @Override
    public int markerCount() {
        return markerCount;
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(
            ActionType.SEARCH_FOR_LIFE,
            "Search For Life",
            new ResourceDeltaAction(new Resources(-1)),
            new Action() {
                @Override
                public boolean check(Game game) {
                    return generationLimit < game.getPlanet().getRound();
                }

                @Override
                public Completable begin(Game game) {
                    return new InstantCompletable(game) {
                        @Override
                        public void complete() {
                            final Card card = game.drawCard();
                            generationLimit = game.getPlanet().getRound();
                            if (card.getTags().hasMicrobe()) {
                                markerCount++;
                            }
                            game.getActionHandler().addPendingIrreversibleAction(new Action() {
                                @Override
                                public Completable begin(Game game) {
                                    return new SelectCardsCompletable(game, Collections.singletonList(card)) {

                                        @Override
                                        public int maxSelection() {
                                            return 0;
                                        }

                                        @Override
                                        public boolean check() {
                                            return true;
                                        }

                                        @Override
                                        public void complete() {
                                            cancel();
                                        }

                                        @Override
                                        public void undo() {
                                            game.getActionHandler().reprocess(this);
                                            game.addMouseListener(mouseListener);
                                            game.repaint();
                                        }

                                        @Override
                                        public void redo() {
                                            cancel();
                                        }

                                        @Override
                                        public String getTitle() {
                                            return "You drew this card";
                                        }
                                    };
                                }
                            });
                        }

                        @Override
                        public void undo() {
                        }

                        @Override
                        public void redo() {
                        }
                    };
                }
            }
        ));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 6% or less");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 VPs if you have any markers", markerCount() + " markers");
    }
}
