package tm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tile {

    public enum Type {
        WATER("images/water.png"),
        GREENERY("images/forest.png"),
        CITY("images/city.png"),
        CAPITAL("images/city.png"),
        MANGROVE("images/forest.png"),
        MISC("images/city.png");

        private final BufferedImage image;

        private Type(String imagePath) {
            image = ImageCache.getImage(imagePath);
        }

        public BufferedImage getImage() {
            return image;
        }
    }

    ;

    // private final static Font font = new Font("Arial", Font.BOLD, 12);
    final Point coords;
    private final TileProperties properties;
    private final List<Tile> neighbors = new ArrayList<>();
    private Type type;
    private Player owner;

    public Tile(int x, int y, Map<Point, Tile> tiles) {
        coords = new Point(x, y);
        Tile current = tiles.get(new Point(x - 1, y));
        current = markAdjacent(tiles.get(new Point(x - 1, y + 1)), current);
        current = markAdjacent(tiles.get(new Point(x, y + 1)), current);
        current = markAdjacent(tiles.get(new Point(x + 1, y)), current);
        current = markAdjacent(tiles.get(new Point(x + 1, y - 1)), current);
        current = markAdjacent(tiles.get(new Point(x, y - 1)), current);
        current = markAdjacent(tiles.get(new Point(x - 1, y)), current);
        tiles.put(coords, this);
        properties = TileProperties.tilePropertiesMap.get(coords);
    }

    public List<Tile> getNeighbors() {
        return neighbors;
    }

    public int getX(int x, double radius) {
        return (int) (x + coords.y * Math.sqrt(3) / 2 * radius + coords.x * Math.sqrt(3) * radius);
    }

    public int getY(int y, double radius) {
        return (int) (y - coords.y * 1.5 * radius);
    }

    private Tile markAdjacent(Tile tile, Tile prev) {
        if (tile != null) {
            neighbors.add(tile);
            tile.neighbors.add(this);
        }
        return tile;
    }

    public boolean setType(Type type) {
        if (this.type != null && type != null) return false;
        this.type = type;
        return true;
    }

    public Type getType() {
        return type;
    }

    public boolean isCity() {
        return isCity(type);
    }

    public static boolean isCity(Tile.Type type) {
        return type == Type.CITY || type == Type.CAPITAL;
    }

    public static boolean isPlacedOnWater(Tile.Type type) {
        return type == Type.WATER || type == Type.MANGROVE;
    }

    public void setOwner(Player player) {
        if (player == null && owner != null) {
            owner.ownedTiles.remove(this);
        }
        owner = player;
        if (player != null) {
            player.ownedTiles.add(this);
        }
    }

    public Player getOwner() {
        return owner;
    }

    public TileProperties getProperties() {
        return properties;
    }

    public void draw(Graphics g) {
        final int px = getX(347, 43.5);
        final int py = getY(350, 43.5);

        if (type != null) {
            g.drawImage(type.getImage(), px - 37, py - 43, null);
        }
        if (owner != null) {
            final Color oldColor = g.getColor();
            g.setColor(owner.getColor());
            g.fillRect(px - 5, py - 5, 10, 10);
            g.setColor(new Color(0x000000));
            g.drawRect(px - 5, py - 5, 10, 10);
            g.setColor(oldColor);
        }
        /*
		final String text = coords.x + "," + coords.y;
		g.setFont(font);
        final FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        g.drawString(text, px - w/2, py + h/2);
        */
    }
}
