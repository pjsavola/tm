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
import tm.action.CardAction;
import tm.action.ResourceDeltaAction;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;

public class SearchForLife extends Card {

    private int markerCount;
    private final Action action = new CardAction(false) {
        @Override
        protected Action getAction(Game game) {
            final Card card = game.drawCard();
            if (card.getTags().hasMicrobe()) {
                markerCount++;
            }
            return new Action() {
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
                            return "Search For Life";
                        }
                    };
                }
            };
        }
    };

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
            action
        ));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 6% or less");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Draw card", "Gain marker if microbe", "3 VPs if you have any markers", "Currently " + markerCount() + " markers");
    }
}