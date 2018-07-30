package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardActionWithCost;
import tm.completable.Completable;
import tm.completable.SelectCardsCompletable;
import tm.requirement.OxygenRequirement;

public class SearchForLife extends CardWithMarkers {

    private final Action action = new CardActionWithCost(false, ActionType.SEARCH_FOR_LIFE, Resources.MONEY.negate()) {

        @Override
        public boolean check(Game game) {
            return game.canDrawCard() && super.check(game);
        }

        @Override
        protected Action getAction(Game game) {
            final Card card = game.drawCard();
            if (card.getTags().has(Tags.Type.MICROBE)) {
                adjustMarkers(1);
            }
            return new Action() {
                @Override
                public Completable begin(Game game) {
                    return new SelectCardsCompletable(game, Collections.singletonList(card), 0, 0, "Search For Life") {
                        @Override
                        public void complete() {
                            game.getDiscardDeck().add(card);
                            game.repaint();
                        }

                        @Override
                        public void undo() {
                            game.getDiscardDeck().remove(card);
                            openWindow();
                            game.getActionHandler().reprocess(this);
                            game.repaint();
                        }

                        @Override
                        public void redo() {
                            game.getDiscardDeck().add(card);
                        }
                    };
                }
            };
        }
    };

    public SearchForLife() {
        super("Search For Life", 3, Tags.SCIENCE, new OxygenRequirement(6, false));
    }

    @Override
    public int getVPs() {
        return getMarkerCount() > 0 ? 3 : 0;
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Oxygen must be 6% or less");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "Draw card", "Gain marker if microbe", "3 VPs if you have any markers", "Currently " + getMarkerCount() + " markers");
    }
}
