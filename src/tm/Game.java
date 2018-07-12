package tm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tm.corporation.Credicor;
import tm.corporation.Ecoline;
import tm.corporation.Helion;
import tm.corporation.InterplanetaryCinematics;
import tm.corporation.Inventrix;
import tm.corporation.MiningGuild;
import tm.corporation.Phoblog;
import tm.corporation.SaturnSystems;
import tm.corporation.Teractor;
import tm.corporation.TharsisRepublic;
import tm.corporation.Thorgate;
import tm.corporation.UnitedNationsMarsInitiative;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Map<Point, Tile> grid = new HashMap<>();
	private final ActionHandler actionHandler;
	private Player currentPlayer = new Player();
	private Planet planet = new Planet();
	private Deque<Card> deck = new ArrayDeque<>();
	private List<Card> discard = new ArrayList<>();
	private Deque<Card> corporationDeck = new ArrayDeque<>();
	private static final Random r = new Random();

	public Game() {
		setPreferredSize(new Dimension(700, 700));
		
		for (int i = 0; i <= 4; i++) new Tile(i, -4, grid);
		for (int i = -1; i <= 4; i++) new Tile(i, -3, grid);
		for (int i = -2; i <= 4; i++) new Tile(i, -2, grid);
		for (int i = -3; i <= 4; i++) new Tile(i, -1, grid);
		for (int i = -4; i <= 4; i++) new Tile(i, 0, grid);
		for (int i = -4; i <= 3; i++) new Tile(i, 1, grid);
		for (int i = -4; i <= 2; i++) new Tile(i, 2, grid);
		for (int i = -4; i <= 1; i++) new Tile(i, 3, grid);
		for (int i = -4; i <= 0; i++) new Tile(i, 4, grid);

		// Temporary bogus cards
		for (int i = 0; i < 100; i++)
			deck.add(new Card("Card", 5, new Tags().space().event(), false));
		corporationDeck.add(new Phoblog());
		corporationDeck.add(new TharsisRepublic());
		corporationDeck.add(new InterplanetaryCinematics());
        corporationDeck.add(new Inventrix());
		corporationDeck.add(new Credicor());
		corporationDeck.add(new Ecoline());
		corporationDeck.add(new Helion());
		corporationDeck.add(new MiningGuild());
		corporationDeck.add(new SaturnSystems());
		corporationDeck.add(new Thorgate());
		corporationDeck.add(new Teractor());
		corporationDeck.add(new UnitedNationsMarsInitiative());
		
		// Initial tiles
		placeInitialTiles();

		actionHandler = new ActionHandler(this);
	}
	
    public Tile getClosestTile(final int x, final int y) {
    	double minDistance = Double.POSITIVE_INFINITY;
    	Tile closestTile = null;
    	final Point clickPoint = new Point(x, y);
		for (final Tile tile : grid.values()) {
			final Point tilePoint = new Point(tile.getX(347, 43.5), tile.getY(350, 43.5));
			final double distance = tilePoint.distance(clickPoint);
			if (distance < minDistance && distance < 37.5 * 0.8) {
				minDistance = distance;
				closestTile = tile;
			}
		}
		return closestTile;
    }
	
    @Override
    public void paintComponent(Graphics g) {
    	final BufferedImage image = ImageCache.getImage("images/mars.png");
    	g.drawImage(image, 0, 0, null);
    	grid.values().forEach(tile -> tile.draw(g));
    	actionHandler.render(g);
    	currentPlayer.render(g);
    	planet.render(g);
    }

    public ActionHandler getActionHandler() {
    	return actionHandler;
    }
    
    public Player getCurrentPlayer() {
    	return currentPlayer;
    }
    
    public Planet getPlanet() {
    	return planet;
    }

    public Card drawCard() {
		if (deck.isEmpty()) {
			// Shuffle the discard to deck
			while (!discard.isEmpty()) {
				deck.push(discard.remove(r.nextInt(discard.size())));
			}
		}
		return deck.pop();
	}

	public Deque<Card> getCorporationDeck() {
	    return corporationDeck;
	}

	public void discardCard(final Card card) {
		discard.add(card);
	}

	// This is different from game rules, due to not needing card costs for randomization.
	private void placeInitialTiles() {
		int cityCount = 2;
		final Tile[] tiles = grid.values().toArray(new Tile[grid.values().size()]);
		while (cityCount > 0) {
			final Tile randomTile = tiles[r.nextInt(tiles.length)];
			if (isSuitable(randomTile)) {
				cityCount--;
				randomTile.setType(Tile.Type.CITY);
				final List<Tile> neighbors = randomTile.getNeighbors();
				while (true) {
					final Tile randomNeighbor = neighbors.get(r.nextInt(neighbors.size()));
					if (isSuitable(randomNeighbor)) {
						randomNeighbor.setType(Tile.Type.GREENERY);
						break;
					}
				}
			}
		}
	}

	private static boolean isSuitable(final Tile tile) {
		if (tile.getType() != null) {
			return false;
		}
		final TileProperties properties = tile.getProperties();
		return properties == null || (!properties.isWater() && !properties.isNoctis());
	}

	public static void main(String[] args) {
        JFrame f = new JFrame();
        Game g = new Game();
        f.addKeyListener(g.getActionHandler().getKeyListener());
        f.setTitle("Terraforming Mars");
        f.setContentPane(g);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
