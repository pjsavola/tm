package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class Player {
	private final static Font font = new Font("Arial", Font.BOLD, 12);
	private Resources resources = new Resources(60);
	private Resources income = new Resources(0);
	private int rating = 20;
	private final Color color = new Color(0xFF0000);
	final Set<Tile> ownedTiles = new HashSet<>();
	
	public Color getColor() {
		return color;
	}
	
	public void adjustResources(final Resources delta) {
		resources.adjust(delta);
	}
	
	public boolean canAdjustResources(final Resources delta) {
		return resources.canAdjust(delta);
	}
	
	public void adjustRating(final int delta) {
		rating += delta;
	}
	
	public void adjustIncome(final Resources delta) {
		income.adjust(delta);
	}
	
	public Resources getIncome() {
		return income.combine(new Resources(rating));
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
    	    .forEach(neighborTile -> freeAdjacentTiles.add(neighborTile)));
    	return freeAdjacentTiles; 
    }
	
	public void render(final Graphics g) {
		final Color oldColor = g.getColor();
		g.setFont(font);
		renderText(g, "Money", resources.money, income.money, 1, 0xFFFF00);
		renderText(g, "Aluminum", resources.aluminum, income.aluminum, 2, 0x8B4513);
		renderText(g, "Titanium", resources.titanium, income.titanium, 3, 0x888888);
		renderText(g, "Plants", resources.plants, income.plants, 4, 0x00FF00);
		renderText(g, "Energy", resources.energy, income.energy, 5, 0x6600FF);
		renderText(g, "Heat", resources.heat, income.heat, 6, 0xFF9900);
		renderText(g, "Rating", rating, 0, 7, 0x0000FF);
		renderText(g, "Points", getPoints(), 0, 8, 0x00FFFF);
        g.setColor(oldColor);
	}
	
	private static void renderText(final Graphics g, final String name, final int amount, final int income, final int i, final int color) {
		g.setColor(new Color(color));
		final String text = name + ": " + amount + (income > 0 ? " (" + income + ")" : "");
		final FontMetrics metrics = g.getFontMetrics(); 
        int h = metrics.getHeight();
        g.drawString(text, 2, (h + 1) * i);
	}
}
