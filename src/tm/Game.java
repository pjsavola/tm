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

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Map<Point, Tile> grid = new HashMap<>();
	private final ActionHandler actionHandler;
	private Player currentPlayer = new Player();
	private Planet planet = new Planet();
	private Deque<Card> deck = new ArrayDeque<>();
	private List<Card> discard = new ArrayList<>();
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
			deck.add(new Card());
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

	public void discardCard(final Card card) {
		discard.add(card);
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
