package tm;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Map<Point, Tile> grid = new HashMap<>();
	private ActionHandler actionHandler = new ActionHandler(this);
	private Player currentPlayer = new Player();
	private Planet planet = new Planet();

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
