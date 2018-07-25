package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import tm.action.Action;
import tm.card.WaterImportFromEuropa;
import tm.corporation.Inventrix;
import tm.effect.DiscountEffect;
import tm.effect.PlayCardEffect;
import tm.effect.ValueEffect;

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
    final List<Card> cards = new ArrayList<>();
    private final List<Card> playedCards = new ArrayList<>();
    Corporation corporation;

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

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public Corporation getCorporation() {
        return corporation;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void cardPlayEffects(Game game, Card selectedCard) {
        if (corporation instanceof PlayCardEffect) {
            final Action action = ((PlayCardEffect) corporation).cardPlayed(selectedCard);
            if (action != null) {
                game.getActionHandler().addPendingAction(action);
            }
        }
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

    public int getSteelValue() {
        int value = 2;
        if (corporation instanceof ValueEffect) {
            value += ((ValueEffect) corporation).getSteelDelta();
        }
        for (Card card : playedCards) {
            if (card instanceof ValueEffect) {
                value += ((ValueEffect) card).getSteelDelta();
            }
        }
        return value;
    }

    public int getTitaniumValue() {
        int value = 3;
        if (corporation instanceof ValueEffect) {
            value += ((ValueEffect) corporation).getTitaniumDelta();
        }
        for (Card card : playedCards) {
            if (card instanceof ValueEffect) {
                value += ((ValueEffect) card).getTitaniumDelta();
            }
        }
        return value;
    }

    public int getDiscount(Card card) {
        int discount = 0;
        if (corporation instanceof DiscountEffect) {
            discount += ((DiscountEffect) corporation).getDiscount(card);
        }
        for (Card playedCard : playedCards) {
            if (playedCard instanceof DiscountEffect) {
                discount += ((DiscountEffect) playedCard).getDiscount(card);
            }
        }
        return Math.min(card.getCost(), discount);
    }

    public boolean fulfillsRequirements(Card card, Planet planet) {
        int tolerance = corporation instanceof Inventrix ? 2 : 0;
        return card.check(planet, tolerance) && card.check(this);
    }

    public void addTags(Tags tags) {
        this.tags = this.tags.combine(tags);
    }

    public void removeTags(Tags tags) {
        this.tags = this.tags.subtract(tags);
    }

    public Tags getTags() {
        return tags;
    }

    public List<Action> getActions() {
        return corporation.getActions();
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
        ownedTiles
            .stream()
            .filter(tile -> tile.getType() == Tile.Type.CAPITAL)
            .findAny()
            .ifPresent(capital -> {
                total.addAndGet(capital
                    .getNeighbors()
                    .stream()
                    .filter(neighborTile -> neighborTile.getType() == Tile.Type.WATER)
                    .count());
            });
        playedCards.forEach(card -> total.addAndGet(card.getVPs()));
        total.addAndGet(ownedTiles
            .stream()
            .filter(tile -> tile.getType() == Tile.Type.GREENERY)
            .count());
        playedCards.stream().filter(card -> card instanceof WaterImportFromEuropa).findAny().ifPresent(_card -> total.addAndGet(tags.getCount(Tags.Type.JOVIAN)));
        return total.intValue();
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
        renderableResources.renderMoney(g, 0, 0, false);
        renderableResources.renderSteel(g, 0, 18, false);
        renderableResources.renderTitanium(g, 0, 36, false);
        renderableResources.renderPlants(g, 0, 54, false);
        renderableResources.renderEnergy(g, 0, 72, false);
        renderableResources.renderHeat(g, 0, 90, false);
        renderableIncome.renderMoney(g, 54, 0, true);
        renderableIncome.renderSteel(g, 54, 18, true);
        renderableIncome.renderTitanium(g, 54, 36, true);
        renderableIncome.renderPlants(g, 54, 54, true);
        renderableIncome.renderEnergy(g, 54, 72, true);
        renderableIncome.renderHeat(g, 54, 90, true);
        renderText(g, "images/icon_tr.png", rating, 7, 0x0000FF);
        renderText(g, "images/icon_vp.png", getPoints(), 8, 0x00FFFF);
        renderText(g, "images/icon_card.png", getCards().size(), 9, 0xCCCCCC);

        tags.renderVertical(g, 0, 700);

        if (corporation != null) {
            final String name = corporation.getName();
            final int w = g.getFontMetrics().stringWidth(name);
            g.setColor(new Color(0xFFFFFF));
            g.drawString(corporation.getName(), 350 - w / 2, 12);
        }
        g.setColor(oldColor);
    }

    private static void renderText(Graphics g, String path, int amount, int i, int color) {
        g.drawImage(ImageCache.getImage(path), 2, i * 18 - 16, null);
        g.setColor(new Color(color));
        g.drawString(Integer.toString(amount), 30, 18 * i - 4);
    }
}
