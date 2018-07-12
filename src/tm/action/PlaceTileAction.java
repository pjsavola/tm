package tm.action;

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

import tm.completable.Completable;
import tm.Game;
import tm.Resources;
import tm.Tile;
import tm.TileProperties;

public class PlaceTileAction implements Action {

	final Tile.Type type;

	public PlaceTileAction(Tile.Type type) {
		this.type = type;
	}
	
	@Override
	public char getKey() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean check(Game game) {
		return true;
	}

	@Override
	public Completable begin(Game game) {
		return new PlaceTileCompletable(game);
			
	}
	
	private class PlaceTileCompletable implements Completable {

		final Game game;
		Point customCursorLocation;
		Tile targetTile;

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
					if (targetTile.getType() != null) {
						System.err.println("There's already a tile");
						return;
					}
					final boolean isWater = targetTile.getProperties() != null && targetTile.getProperties().isWater();
					if (isWater && type != Tile.Type.WATER) {
						System.err.println("Reserved for water");
						return;
					}
					if (!isWater && type == Tile.Type.WATER) {
						System.err.println("Not for water");
						return;
					}
					final boolean isNoctis = targetTile.getProperties() != null && targetTile.getProperties().isNoctis();
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
						final Set<Tile> freeAdjacentTiles = game.getCurrentPlayer().getFreeAdjacentTiles();
						if (!freeAdjacentTiles.isEmpty() && !freeAdjacentTiles.contains(targetTile)) {
							System.err.println("Greenery not adjacent to your other tiles");
							return;
						}
					}
					game.getActionHandler().completed(PlaceTileCompletable.this);
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

		private PlaceTileCompletable(Game game) {
			this.game = game;
			game.addMouseListener(mouseListener);
			game.addMouseMotionListener(mouseMotionListener);
			customCursorLocation = MouseInfo.getPointerInfo().getLocation();
			updateCursor();
		}
		
		@Override
		public boolean remove(Set<Completable> completedSet) {
			return completedSet.remove(this);
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
			targetTile.setType(type);
			if (type != Tile.Type.WATER) {
				targetTile.setOwner(game.getCurrentPlayer());
			}
			int sum = 0;
			for (Tile tile : targetTile.getNeighbors()) {
				if (tile.getType() == Tile.Type.WATER) {
					sum += 2;
				}
			}
			final TileProperties p = targetTile.getProperties();
			if (sum > 0 || p != null) {
				final Resources money = new Resources(sum);
				final Resources resources = p == null ? money : p.getResources().combine(money);
				final Action bonusAction = new ResourceDeltaAction(resources);
				if (bonusAction.check(game)) {
					game.getActionHandler().addPendingAction(bonusAction);
				}
				if (p != null && p.getCards() > 0) {
					game.getActionHandler().addPendingIrreversibleAction(new DrawCardsAction(p.getCards(), false, false));
				}
			}
			cancel();
		}
		

		@Override
		public void undo() {
			targetTile.setType(null);
			targetTile.setOwner(null);
			game.repaint();
		}

		@Override
		public void redo() {
			targetTile.setType(type);
			if (type != Tile.Type.WATER) {
				targetTile.setOwner(game.getCurrentPlayer());
			}
			game.repaint();
		}
		
		@Override
		public void paint(Graphics g) {
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
}
