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

import com.sun.istack.internal.Nullable;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tile;
import tm.TileProperties;
import tm.card.ArcticAlgae;
import tm.completable.Completable;
import tm.corporation.MiningGuild;
import tm.corporation.TharsisRepublic;

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
                    if (Tile.isCity(type)) {
                        if (targetTile.getNeighbors().stream().anyMatch(tile -> Tile.isCity(tile.getType()))) {
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

    public static void placeTile(Game game, Tile targetTile, Tile.Type type) {
        targetTile.setType(type);
        if (type != Tile.Type.WATER) {
            targetTile.setOwner(game.getCurrentPlayer());
        } else {
            // TODO: Find player who owns the card if we need support for more than 1 player
            if (game.getCurrentPlayer().getPlayedCards().stream().anyMatch(card -> card instanceof ArcticAlgae)) {
                game.getActionHandler().addPendingAction(new IncomeDeltaAction(Resources.PLANT_2));
            }
        }
        int sum = 0;
        for (Tile tile : targetTile.getNeighbors()) {
            if (tile.getType() == Tile.Type.WATER) {
                sum += 2;
            }
        }
        if (Tile.isCity(type)) {
            final Player tharsisRepublicPlayer = game.getPlayer(TharsisRepublic.class);
            if (tharsisRepublicPlayer != null) {
                game.getActionHandler().addPendingAction(new IncomeDeltaAction(Resources.MONEY, tharsisRepublicPlayer));
            }
            if (tharsisRepublicPlayer == game.getCurrentPlayer()) {
                game.getActionHandler().addPendingAction(new ResourceDeltaAction(new Resources(3)));
            }
        }
        final TileProperties p = targetTile.getProperties();
        if (sum > 0 || p != null) {
            final Resources resources;
            if (p == null) {
                resources = new Resources(sum);
            } else {
                resources = new Resources(sum, p.getSteel(), p.getTitanium(), p.getPlants(), 0, 0);
                if (type == Tile.Type.MINING_AREA) {
                    game.getActionHandler().addPendingAction(new IncomeDeltaAction(p.getSteel() > 0 ? Resources.STEEL : Resources.TITANIUM));
                }
            }
            final Action bonusAction = new ResourceDeltaAction(resources);
            if (bonusAction.check(game)) {
                game.getActionHandler().addPendingAction(bonusAction);
            }
            if (game.getCurrentPlayer().getCorporation() instanceof MiningGuild && p != null && (p.getSteel() > 0 || p.getTitanium() > 0)) {
                game.getActionHandler().addPendingAction(new IncomeDeltaAction(Resources.STEEL));
            }
            if (p != null && p.getCards() > 0) {
                game.getActionHandler().addPendingIrreversibleAction(new DrawCardsAction(p.getCards(), false, false));
            }
        }
    }
}
