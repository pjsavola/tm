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
import tm.card.ResearchOutpost;
import tm.card.SpaceStation;
import tm.card.WaterImportFromEuropa;
import tm.corporation.Inventrix;
import tm.corporation.Phoblog;
import tm.corporation.Teractor;
import tm.corporation.Thorgate;

public class Player {
    private static final Font font = new Font("Arial", Font.BOLD, 12);
    private Resources resources = new Resources(0);
    private Resources income = new Resources(0);
    private Resources resourcesDelta = new Resources(0);
    private Resources incomeDelta = new Resources(0);
    private Tags tags = new Tags();
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
        resources.adjust(delta);
    }

    public boolean canAdjustResources(Resources delta) {
        return resources.canAdjust(delta);
    }

    public void setResourcesDelta(Resources delta) {
        resourcesDelta = delta;
    }

    public void setIncomeDelta(Resources delta) {
        incomeDelta = delta;
    }

    public boolean canAdjustIncome(Resources delta) {
        final Resources newIncome = income.combine(delta);
        return newIncome.steel >= 0 && newIncome.titanium >= 0 && newIncome.plants >= 0 && newIncome.energy >= 0 && newIncome.heat >= 0;
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
        income.adjust(delta);
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

    public List<Card> getPlayedCards() {
        return playedCards;
    }

    public Resources getIncome() {
        final int leftOverEnergy = resources.energy;
        return income.combine(new Resources(rating, 0, 0, 0, -leftOverEnergy, leftOverEnergy));
    }

    public int getSteel() {
        return resources.steel;
    }

    public int getTitanium() {
        return resources.titanium;
    }

    public int getSteelValue() {
        return 2;
    }

    public int getTitaniumValue() {
        if (corporation instanceof Phoblog) {
            return 4;
        }
        return 3;
    }

    public int getDiscount(Card card) {
        int discount = 0;
        if (card.getTags().hasPower() && corporation instanceof Thorgate) {
            discount += 3;
        }
        if (card.getTags().hasEarth() && corporation instanceof Teractor) {
            discount += 3;
        }
        if (playedCards.stream().anyMatch(c -> c instanceof ResearchOutpost)) {
            discount += 1;
        }
        if (card.getTags().hasSpace() && playedCards.stream().anyMatch(c -> c instanceof SpaceStation)) {
            discount += 2;
        }
        return discount;
    }

    public boolean fulfillsRequirements(Card card, Planet planet) {
        int tolerance = corporation instanceof Inventrix ? 2 : 0;
        return card.check(planet, tolerance) && card.check(this);
    }

    public void addTags(Tags tags) {
        this.tags.combine(tags, true);
    }

    public void removeTags(Tags tags) {
        this.tags.combine(tags, false);
    }

    public boolean hasTags(Tags tags) {
        return this.tags.hasTags(tags);
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
        playedCards.stream().filter(card -> card instanceof WaterImportFromEuropa).findAny().ifPresent(_card -> total.addAndGet(tags.jovian));
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
        renderText(g, "images/icon_money.png", renderableResources.money, renderableIncome.money, 1, 0xFFFF00);
        renderText(g, "images/icon_steel.png", renderableResources.steel, renderableIncome.steel, 2, 0x8B4513);
        renderText(g, "images/icon_titanium.png", renderableResources.titanium, renderableIncome.titanium, 3, 0x888888);
        renderText(g, "images/icon_plant.png", renderableResources.plants, renderableIncome.plants, 4, 0x00FF00);
        renderText(g, "images/icon_energy.png", renderableResources.energy, renderableIncome.energy, 5, 0x6600FF);
        renderText(g, "images/icon_heat.png", renderableResources.heat, renderableIncome.heat, 6, 0xFF9900);
        renderText(g, "images/icon_tr.png", rating, 0, 7, 0x0000FF);
        renderText(g, "images/icon_vp.png", getPoints(), 0, 8, 0x00FFFF);
        renderText(g, "images/icon_card.png", getCards().size(), 0, 9, 0xCCCCCC);

        tags.renderVertical(g, 2, 682);

        if (corporation != null) {
            final String name = corporation.getName();
            final int w = g.getFontMetrics().stringWidth(name);
            g.setColor(new Color(0xFFFFFF));
            g.drawString(corporation.getName(), 350 - w / 2, 12);
        }
        g.setColor(oldColor);
    }

    private static void renderText(Graphics g, String path, int amount, int income, int i, int color) {
        g.drawImage(ImageCache.getImage(path), 2, i * 18 - 16, null);
        g.setColor(new Color(color));
        g.drawString(Integer.toString(amount), 30, 18 * i - 4);
        if (income > 0) {
            g.drawString("+" + income, 50, 18 * i - 4);
        } else if (income < 0) {
            g.drawString(Integer.toString(income), 50, 18 * i - 4);
        }
    }
}
