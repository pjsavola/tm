package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Player {
	private static final Font font = new Font("Arial", Font.BOLD, 12);
	private Resources resources = new Resources(0);
	private Resources income = new Resources(0);
	private int rating = 20;
	private final Color color = new Color(0xFF0000);
	final Set<Tile> ownedTiles = new HashSet<>();
	final List<Card> cards = new ArrayList<>();
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

	public boolean canAdjustIncome(Resources delta) {
	    final Resources newIncome = income.combine(delta);
	    return newIncome.steel >= 0 && newIncome.titanium >= 0 && newIncome.plants >= 0 && newIncome.energy >= 0 && newIncome.heat >= 0;
	}

	public void adjustRating(int delta) {
		rating += delta;
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

	public int getPoints() {
		final AtomicLong total = new AtomicLong(rating);
		ownedTiles
			.stream()
			.filter(tile -> tile.getType() == Tile.Type.CITY)
			.forEach(tile -> {
				total.addAndGet(tile
					.getNeighbors()
					.stream()
					.filter(neighborTile -> neighborTile.getType() == Tile.Type.GREENERY)
					.count());
			});
		total.addAndGet(ownedTiles
			.stream()
			.filter(tile -> tile.getType() == Tile.Type.GREENERY)
			.count());
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
		renderText(g, "Money", resources.money, income.money, 1, 0xFFFF00);
		renderText(g, "Steel", resources.steel, income.steel, 2, 0x8B4513);
		renderText(g, "Titanium", resources.titanium, income.titanium, 3, 0x888888);
		renderText(g, "Plants", resources.plants, income.plants, 4, 0x00FF00);
		renderText(g, "Energy", resources.energy, income.energy, 5, 0x6600FF);
		renderText(g, "Heat", resources.heat, income.heat, 6, 0xFF9900);
		renderText(g, "Rating", rating, 0, 7, 0x0000FF);
		renderText(g, "Points", getPoints(), 0, 8, 0x00FFFF);
		renderText(g, "Cards", getCards().size(), 0, 9, 0xCCCCCC);
		if (corporation != null) {
			final String name = corporation.getName();
			final int w = g.getFontMetrics().stringWidth(name);
			g.setColor(new Color(0xFFFFFF));
			g.drawString(corporation.getName(), 350 - w / 2, 12);
		}
        g.setColor(oldColor);
	}
	
	private static void renderText(Graphics g, String name, int amount, int income, int i, int color) {
		g.setColor(new Color(color));
		final String text = name + ": " + amount + (income > 0 ? " (" + income + ")" : "");
		final FontMetrics metrics = g.getFontMetrics(); 
        int h = metrics.getHeight();
        g.drawString(text, 2, (h + 1) * i);
	}
}
