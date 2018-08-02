package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

import com.sun.istack.internal.Nullable;
import tm.action.Action;
import tm.effect.DiscountEffect;
import tm.effect.PlayCardEffect;
import tm.effect.RequirementEffect;
import tm.effect.ScoringEffect;
import tm.effect.ValueEffect;
import tm.requirement.Requirement;

public class Player {
    private static final Font font = new Font("Arial", Font.BOLD, 12);
    private Resources resources = new Resources(0);
    private Resources income = new Resources(0);
    private Resources resourcesDelta = new Resources(0);
    private Resources incomeDelta = new Resources(0);
    private Tags tags = Tags.EMPTY;
    private int rating = 20;
    private int savedRating = 20;
    private final Color color = new Color(0xFF0000);
    final Set<Tile> ownedTiles = new HashSet<>();
    final Set<Card> cards = new TreeSet<>();
    private final List<Card> playedCards = new ArrayList<>();

    public Color getColor() {
        return color;
    }

    public void adjustResources(Resources delta) {
        resources = resources.combine(delta);
    }

    public boolean canAdjustResources(Resources delta) {
        return resources.combine(delta).isValidState();
    }

    public void setResourcesDelta(Resources delta) {
        resourcesDelta = delta;
    }

    public void setIncomeDelta(Resources delta) {
        incomeDelta = delta;
    }

    public boolean canAdjustIncome(Resources delta) {
        return income.combine(delta).isValidIncome();
    }

    public void adjustRating(int delta) {
        rating += delta;
    }

    public void saveRating() {
        savedRating = rating;
    }

    public boolean hasIncreasedRating() {
        return rating > savedRating;
    }

    public void adjustIncome(Resources delta) {
        income = income.combine(delta);
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void cardPlayEffects(Game game, Card selectedCard) {
        playedCards.forEach(card -> {
            if (card instanceof PlayCardEffect) {
                final Action action = ((PlayCardEffect) card).cardPlayed(selectedCard);
                if (action != null) {
                    game.getActionHandler().addPendingAction(action);
                }
            }
        });
    }

    public void playCard(Card card) {
        card.setOwner(this);
        playedCards.add(card);
    }

    public void unplayCard(Card card) {
        card.setOwner(null);
        playedCards.remove(card);
    }

    public List<Card> getPlayedCards() {
        return playedCards;
    }

    public Resources getIncome() {
        return income.getTurnIncome(rating, resources);
    }

    public int getSteel() {
        return resources.getSteel();
    }

    public int getTitanium() {
        return resources.getTitanium();
    }

    public int getEnergy() {
        return resources.getEnergy();
    }

    public int getSteelValue() {
        int value = 2;
        for (Card card : playedCards) {
            if (card instanceof ValueEffect) {
                value += ((ValueEffect) card).getSteelDelta();
            }
        }
        return value;
    }

    public int getTitaniumValue() {
        int value = 3;
        for (Card card : playedCards) {
            if (card instanceof ValueEffect) {
                value += ((ValueEffect) card).getTitaniumDelta();
            }
        }
        return value;
    }

    public int getDiscount(Card card, int extraDiscount) {
        int discount = extraDiscount;
        for (Card playedCard : playedCards) {
            if (playedCard instanceof DiscountEffect) {
                discount += ((DiscountEffect) playedCard).getDiscount(card);
            }
        }
        return Math.min(card.getCost(), discount);
    }

    public boolean fulfillsRequirements(Card card, Game game, int extraTolerance) {
        final Requirement requirement = card.getRequirement();
        if (requirement == null) {
            return true;
        }
        int tolerance = extraTolerance;
        for (Card playedCard : playedCards) {
            if (playedCard instanceof RequirementEffect) {
                tolerance += ((RequirementEffect) playedCard).getTolerance();
            }
        }
        return requirement.check(game, tolerance);
    }

    public void addTags(Tags tags) {
        if (tags.has(Tags.Type.EVENT)) {
            this.tags = this.tags.combine(Tags.EVENT);
        } else {
            this.tags = this.tags.combine(tags);
        }
    }

    public void removeTags(Tags tags) {
        if (tags.has(Tags.Type.EVENT)) {
            this.tags = this.tags.subtract(Tags.EVENT);
        } else {
            this.tags = this.tags.subtract(tags);
        }
    }

    public Tags getTags() {
        return tags;
    }

    public int getPoints() {
        final AtomicLong total = new AtomicLong(rating);
        ownedTiles
            .stream()
            .filter(Tile::isCity)
            .forEach(tile -> {
                total.addAndGet(tile
                    .getNeighbors()
                    .stream()
                    .filter(neighborTile -> neighborTile.getType() == Tile.Type.GREENERY)
                    .count());
            });
        playedCards.forEach(card -> total.addAndGet(card.getVPs()));
        total.addAndGet(getGreeneryCount());
        for (Card playedCard : playedCards) {
            if (playedCard instanceof ScoringEffect) {
                total.addAndGet(((ScoringEffect) playedCard).getVPs(this));
            }
        }
        return total.intValue();
    }

    public int getGreeneryCount() {
        return (int) ownedTiles.stream().filter(Tile::isGreenery).count();
    }

    // Nasty hack...
    public int getCityCount() {
        return (int) ownedTiles.stream().filter(Tile::isCity).count() + 2;
    }

    @Nullable
    public Tile findTile(Tile.Type type) {
        return ownedTiles
            .stream()
            .filter(tile -> tile.getType() == type)
            .findAny()
            .orElse(null);
    }

    public Set<Tile> getFreeAdjacentTiles() {
        final Set<Tile> freeAdjacentTiles = new HashSet<>();
        ownedTiles.forEach(tile -> tile
            .getNeighbors()
            .stream()
            .filter(neighborTile -> neighborTile.getOwner() == null)
            .forEach(freeAdjacentTiles::add));
        return freeAdjacentTiles;
    }

    public void render(Graphics g) {
        final Color oldColor = g.getColor();
        g.setFont(font);
        final Resources renderableResources = resources.combine(resourcesDelta);
        final Resources renderableIncome = income.combine(incomeDelta);
        renderableResources.renderMoney(g, 0, 0, false, true);
        renderableResources.renderSteel(g, 0, 18, false, true);
        renderableResources.renderTitanium(g, 0, 36, false, true);
        renderableResources.renderPlants(g, 0, 54, false, true);
        renderableResources.renderEnergy(g, 0, 72, false, true);
        renderableResources.renderHeat(g, 0, 90, false, true);
        renderableIncome.renderMoney(g, 54, 0, true, true);
        renderableIncome.renderSteel(g, 54, 18, true, true);
        renderableIncome.renderTitanium(g, 54, 36, true, true);
        renderableIncome.renderPlants(g, 54, 54, true, true);
        renderableIncome.renderEnergy(g, 54, 72, true, true);
        renderableIncome.renderHeat(g, 54, 90, true, true);
        renderText(g, "images/icon_tr.png", rating, 7, 0x0000FF);
        renderText(g, "images/icon_vp.png", getPoints(), 8, 0x00FFFF);
        renderText(g, "images/icon_card.png", getCards().size(), 9, 0xCCCCCC);

        tags.renderVertical(g, 0, 700);

        // First card is the corporation
        if (!playedCards.isEmpty()) {
            final String name = playedCards.get(0).getName();
            final int w = g.getFontMetrics().stringWidth(name);
            g.setColor(new Color(0xFFFFFF));
            g.drawString(playedCards.get(0).getName(), 350 - w / 2, 12);
        }
        g.setColor(oldColor);
    }

    private static void renderText(Graphics g, String path, int amount, int i, int color) {
        g.drawImage(ImageCache.getImage(path), 2, i * 18 - 16, null);
        g.setColor(new Color(color));
        g.drawString(Integer.toString(amount), 30, 18 * i - 4);
    }
}
