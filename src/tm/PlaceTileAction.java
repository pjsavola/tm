package tm;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Set;

public class PlaceTileAction implements Action {

	final Game game;
	final Tile.Type type;
	Point customCursorLocation;
	Tile targetTile;
	Resources bonusResources;
	
	final MouseListener mouseListener = new MouseListener() {
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			targetTile = game.getClosestTile(arg0.getX(), arg0.getY());
			if (targetTile != null) {
				final boolean isWater = targetTile.properties != null && targetTile.properties.isWater;
				if (isWater && type != Tile.Type.WATER) {
					System.err.println("Reserved for water");
					return;
				}
				if (!isWater && type == Tile.Type.WATER) {
					System.err.println("Not for water");
					return;
				}
				final boolean isNoctis = targetTile.properties != null && targetTile.properties.isNoctis;
				if (isNoctis) {
					System.err.println("Reserved for Noctis City");
					return;
				}
				if (type == Tile.Type.CITY) {
					if (targetTile.getNeighbors().stream().anyMatch(tile -> tile.getType() == Tile.Type.CITY)) {
						System.err.println("Too close to another city");
						return;
					}
				}
				if (type == Tile.Type.GREENERY) {
					final Set<Tile> freeAdjacentTiles = game.getFreeAdjacentTiles();
					if (!freeAdjacentTiles.isEmpty() && !freeAdjacentTiles.contains(targetTile)) {
						System.err.println("Greenery not adjacent to your other tiles");
						return;
					}
				}
				game.getActionHandler().actionFinished(PlaceTileAction.this);
			}
		}
	};
	
	final MouseMotionListener mouseMotionListener = new MouseMotionListener() {
		@Override
		public void mouseMoved(MouseEvent e) {
			customCursorLocation = new Point(e.getX(), e.getY());
			game.repaint();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
		}
	};
	
	public PlaceTileAction(final Tile.Type type, final Game game) {
		this.type = type;
		this.game = game;
	}
	
	@Override
	public char getKey() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean check() {
		return true;
	}

	@Override
	public void begin() {
		game.addMouseListener(mouseListener);
		game.addMouseMotionListener(mouseMotionListener);
		customCursorLocation = MouseInfo.getPointerInfo().getLocation();
		updateCursor();
	}

	@Override
	public void cancel() {
		game.removeMouseListener(mouseListener);
		game.removeMouseMotionListener(mouseMotionListener);
		game.setCursor(Cursor.getDefaultCursor());
		game.repaint();
	}

	@Override
	public void complete() {
		// TODO: Bonus cards
		targetTile.setType(type);
		if (type != Tile.Type.WATER) {
			targetTile.setOwner(game.getCurrentPlayer());
		}
		int sum = 0;
		for (final Tile tile : targetTile.getNeighbors()) {
			if (tile.getType() == Tile.Type.WATER) {
				sum += 2;
			}
		}
		final TileProperties p = targetTile.properties;
		if (p != null) {
			bonusResources = new Resources(sum, p.aluminum, p.titanium, p.plants, 0, 0);
		} else {
			bonusResources = new Resources(sum);
		}
		game.getCurrentPlayer().adjustResources(bonusResources);
		cancel();
	}
	

	@Override
	public void undo() {
		targetTile.setType(null);
		targetTile.setOwner(null);
		game.getCurrentPlayer().adjustResources(bonusResources.negate());
		game.repaint();
	}

	@Override
	public void redo() {
		targetTile.setType(type);
		if (type != Tile.Type.WATER) {
			targetTile.setOwner(game.getCurrentPlayer());
		}
		game.getCurrentPlayer().adjustResources(bonusResources);
		game.repaint();
	}
	
	@Override
	public void paint(final Graphics g) {
		if (type != null && customCursorLocation != null) {
			g.drawImage(type.getImage(), customCursorLocation.x - 37, customCursorLocation.y - 43, null);
		}
	}
	
	private void updateCursor() {
		final Toolkit kit = Toolkit.getDefaultToolkit();
		final Cursor cursor = kit.createCustomCursor(
			new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), 
			new Point(0, 0), 
			"Blank");
		game.setCursor(cursor);
		game.repaint();
	}
}
