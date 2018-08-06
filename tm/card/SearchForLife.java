package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.CardWithMarkers;
import tm.Game;
import tm.ImageCache;
import tm.Renderer;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.CardActionWithCost;
import tm.completable.Completable;
import tm.completable.SelectItemsCompletable;
import tm.requirement.OxygenRequirement;

public class SearchForLife extends CardWithMarkers {

    private final CardAction action = new CardActionWithCost(false, ActionType.SEARCH_FOR_LIFE, Resources.MONEY.negate()) {

        @Override
        public boolean check(Game game) {
            return game.canDrawCard() && super.check(game);
        }

        @Override
        protected Action getAction(Game game) {
            return new Action() {
                @Override
                public Completable begin(Game game) {
                    final Card card = game.drawCard();
                    if (card.getTags().has(Tags.Type.MICROBE)) {
                        adjustMarkers(1);
                    }
                    return new SelectItemsCompletable<Card>(game, Collections.singletonList(card), 0, 0, "Search For Life") {
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

                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    g.drawImage(ImageCache.getImage("images/icon_card.png"), x, y, null);
                    final Point p = Tags.MICROBE.render(g, x + 14, y, true);
                    g.setColor(Color.WHITE);
                    final Point p2 = Renderer.renderText(g, "?", p.x + 1, y + 3, false);
                    return Renderer.renderMarker(g, p2.x + 2, y);
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
    public String getRatio() {
        return "3?";
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }
}
