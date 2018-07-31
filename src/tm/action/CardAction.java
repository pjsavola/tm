package tm.action;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Selectable;
import tm.completable.Completable;

public abstract class CardAction implements Action, Selectable {

    private int usedOnRound;
    private final boolean undoable;
    @Nullable
    private CardAction alternativeAction;
    private final ActionType type;

    public CardAction(boolean undoable, ActionType type) {
        this.undoable = undoable;
        this.type = type;
    }

    @Override
    public ActionType getType() {
        return type;
    }

    @Override
    public boolean isUndoable() {
        return undoable;
    }

    public void setAlternativeAction(CardAction action) {
        alternativeAction = action;
    }

    @Override
    public boolean check(Game game) {
        final int round = game.getPlanet().getRound();
        if (usedOnRound >= round) {
            return false;
        }
        if (alternativeAction != null && alternativeAction.usedOnRound >= round) {
            return false;
        }
        final Action cardAction = getAction(game);
        return cardAction == null || cardAction.check(game);
    }

    @Override
    public Completable begin(Game game) {
        return new CardActionCompletable(game, this);
    }

    @Nullable
    protected abstract Action getAction(Game game);

    public Resources getResourceDelta(Game game) {
        return Resources.EMPTY;
    }

    public Resources getIncomeDelta(Game game) {
        return Resources.EMPTY;
    }

    @Override
    public void renderTitle(Graphics g, int x, int y) {
        g.setFont(Card.FONT);
        final FontMetrics metrics = g.getFontMetrics();
        final int w = metrics.stringWidth(getDescription());

        // Draw background
        g.setColor(Color.BLACK);
        g.fillRect(x, y, Card.WIDTH, Card.TITLE_HEIGHT);

        // Draw border
        g.setColor(Color.GRAY);
        g.drawRect(x, y, Card.WIDTH, Card.TITLE_HEIGHT);

        // Draw text
        g.setColor(Color.WHITE);
        g.drawString(getDescription(), x + (Card.WIDTH - w) / 2 - 8, y + 14);
    }

    @Override
    public void renderContent(Graphics g, int x, int y, Game game) {
        // Draw background
        g.setColor(Card.BG_COLOR);
        g.fillRect(x, y, Card.WIDTH, Card.CARD_HEIGHT);

        // Draw border
        g.setColor(Color.WHITE);
        g.drawRect(x, y, Card.WIDTH, Card.CARD_HEIGHT);

        // Draw resources and income
        int offset = getResourceDelta(game).render(g, x, y + Card.TITLE_HEIGHT + 20, false);
        getIncomeDelta(game).render(g, x, y + Card.TITLE_HEIGHT + 20 + offset, true);

        // Draw content
    }

    @Override
    public void render(Graphics g, int x, int y) {
        // TODO
    }

    protected static class CardActionCompletable implements Completable {
        private final CardAction action;
        private final Game game;

        protected CardActionCompletable(Game game, CardAction action) {
            this.game = game;
            this.action = action;
            game.getActionHandler().completed(this);
        }

        @Override
        public void complete() {
            final Action cardAction = action.getAction(game);
            action.usedOnRound = game.getPlanet().getRound();
            if (cardAction != null) {
                game.getActionHandler().addPendingAction(cardAction);
            }
        }

        @Override
        public void undo() {
            if (action.isUndoable()) {
                action.usedOnRound = game.getPlanet().getRound() - 1;
            }
        }

        @Override
        public void redo() {
            if (action.isUndoable()) {
                action.usedOnRound = game.getPlanet().getRound();
            }
        }
    }
}
