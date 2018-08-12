package tm.action;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Set;

import javax.annotation.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tile;
import tm.TileProperties;
import tm.completable.Completable;
import tm.effect.PlaceTileEffect;

public class PlaceTileAction implements Action {

    private final Tile.Type type;
    private final boolean isolated;

    public PlaceTileAction(Tile.Type type) {
        this(type, false);
    }

    public PlaceTileAction(Tile.Type type, boolean isolated) {
        this.type = type;
        this.isolated = isolated;
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

        private final Game game;
        private Point customCursorLocation;
        private Tile targetTile;
        @Nullable
        private Player owner; // Needed for Land Claim

        private final MouseListener mouseListener = new MouseListener() {
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
                    } else if (targetTile.getOwner() != null && targetTile.getOwner() != game.getCurrentPlayer()) {
                        System.err.println("Land is claimed by another player");
                        return;
                    }
                    final boolean isWater = targetTile.getProperties() != null && targetTile.getProperties().isWater();
                    if (isWater && !Tile.isPlacedOnWater(type)) {
                        System.err.println("Reserved for water");
                        return;
                    }
                    if (!isWater && Tile.isPlacedOnWater(type)) {
                        System.err.println("Not for water");
                        return;
                    }
                    final boolean isNoctis = targetTile.getProperties() != null && targetTile.getProperties().isNoctis();
                    if (isNoctis) {
                        System.err.println("Reserved for Noctis City");
                        return;
                    }
                    if (type == Tile.Type.URBANIZED_AREA) {
                        if (targetTile.getNeighbors().stream().filter(Tile::isCity).count() < 2) {
                            System.err.println("Need at least 2 adjacent cities");
                            return;
                        }
                    } else if (Tile.isCity(type)) {
                        if (targetTile.getNeighbors().stream().anyMatch(Tile::isCity)) {
                            System.err.println("Too close to another city");
                            return;
                        }
                    }
                    if (isolated) {
                        if (targetTile.getNeighbors().stream().anyMatch(tile -> tile.getType() != null)) {
                            System.err.println("Too close to another tile");
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
                    if (type == Tile.Type.MINING_AREA) {
                        final Set<Tile> freeAdjacentTiles = game.getCurrentPlayer().getFreeAdjacentTiles();
                        if (freeAdjacentTiles.isEmpty() || !freeAdjacentTiles.contains(targetTile)) {
                            System.err.println("Mining Area not adjacent to your other tiles");
                            return;
                        }
                        if (targetTile.getProperties() == null || (targetTile.getProperties().getSteel() == 0 && targetTile.getProperties().getTitanium() == 0)) {
                            System.err.println("Mining Area must be placed on steel or titanium");
                            return;
                        }
                    }
                    if (type == Tile.Type.MINING_RIGHTS) {
                        if (targetTile.getProperties() == null || (targetTile.getProperties().getSteel() == 0 && targetTile.getProperties().getTitanium() == 0)) {
                            System.err.println("Mining Rights must be placed on steel or titanium");
                            return;
                        }
                    }
                    if (type == Tile.Type.INDUSTRIAL_CENTER) {
                        if (targetTile.getNeighbors().stream().filter(Tile::isCity).count() < 1) {
                            System.err.println("Need at least 1 adjacent city");
                            return;
                        }
                    }
                    if (type == Tile.Type.ECOLOGICAL_ZONE) {
                        if (targetTile.getNeighbors().stream().filter(Tile::isGreenery).count() < 1) {
                            System.err.println("Need at least 1 adjacent greenery");
                            return;
                        }
                    }
                    if (type == Tile.Type.LAVA_FLOWS && (targetTile.getProperties() == null || !targetTile.getProperties().isVolcano())) {
                        System.err.println("Lava flows must be placed on volcano");
                        return;
                    }
                    game.getActionHandler().completed(PlaceTileCompletable.this);
                }
            }
        };

        private final MouseMotionListener mouseMotionListener = new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                customCursorLocation = new Point(e.getX(), e.getY());
                game.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        };

        public PlaceTileCompletable(Game game) {
            this.game = game;
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
            owner = targetTile.getOwner();
            placeTile(game, targetTile, type);
            cancel();
        }

        @Override
        public void undo() {
            targetTile.setType(null);
            targetTile.setOwner(owner);
            game.repaint();
        }

        @Override
        public void redo() {
            targetTile.setType(type);
            if (!Tile.isOcean(type)) {
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

    public static void placeTile(Game game, Tile targetTile, Tile.Type type) {
        targetTile.setType(type);
        if (!Tile.isOcean(type)) {
            targetTile.setOwner(game.getCurrentPlayer());
        }
        int sum = 0;
        for (Tile tile : targetTile.getNeighbors()) {
            if (Tile.isOcean(tile.getType())) {
                sum += 2;
            }
        }
        final TileProperties p = targetTile.getProperties();
        if (sum > 0 || p != null) {
            final Resources resources;
            if (p == null) {
                resources = new Resources(sum);
            } else {
                resources = new Resources(sum, p.getSteel(), p.getTitanium(), p.getPlants(), 0, 0);
            }
            final Action bonusAction = new ResourceDeltaAction(resources);
            if (bonusAction.check(game)) {
                game.getActionHandler().addPendingAction(bonusAction);
            }
            if (p != null && p.getCards() > 0) {
                game.getActionHandler().addPendingAction(new DrawCardsAction(p.getCards(), false, false));
            }
        }
        game.getCurrentPlayer().getPlayedCards().forEach(card -> {
            if (card instanceof PlaceTileEffect) {
                final Action action = ((PlaceTileEffect) card).tilePlaced(targetTile);
                if (action != null) {
                    game.getActionHandler().addPendingAction(action);
                }
            }
        });
    }

    @Override
    public Point render(Graphics g, int x, int y, Game game) {
        final Image image = type.getIcon();
        g.drawImage(image, x, y, null);
        return new Point(x + image.getWidth(null), y + image.getHeight(null));
    }
}
