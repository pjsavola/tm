package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Planet;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.DrawCardsAction;
import tm.action.ResourceDeltaAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

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
        return 0;
    }

    @Override
    public int markerCount() {
        return 0;
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new Action() {
            @Override
            public boolean check(Game game) {
                return generationLimit < game.getPlanet().getRound() &&
                    game.getCurrentPlayer().canAdjustResources(new Resources(-1));
            }
            @Override
            public Completable begin(Game game) {
                game.getActionHandler().addPendingIrreversibleAction(
                    new ActionChain(
                        null,
                        "Search For Life",
                        new ResourceDeltaAction(new Resources(-1)),
                        new DrawCardsAction(1, false, false),
                        new Action() {
                            @Override
                            public Completable begin(Game game) {
                                final List<Card> hand = game.getCurrentPlayer().getCards();
                                final Card card = hand.remove(hand.size() - 1);
                                return new InstantCompletable(game) {
                                    @Override
                                    public void complete() {
                                        generationLimit = game.getPlanet().getRound();
                                        if (card.getTags().hasMicrobe()) {
                                            markerCount++;
                                        }
                                    }
                                    @Override
                                    public void undo() {
                                        throw new UnsupportedOperationException();
                                    }
                                    @Override
                                    public void redo() {
                                        throw new UnsupportedOperationException();
                                    }
                                };
                            }
                        }
                    )
                );
                return new InstantCompletable(game) {
                    @Override
                    public void complete() {
                    }
                    @Override
                    public void undo() {
                    }
                    @Override
                    public void redo() {
                    }
                };
            }
        });
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 6% or less");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("3 VPs if you have any markers here", markerCount() + " markers");
    }
}
