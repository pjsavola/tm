package tm;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class TileProperties {
    private final int steel;
    private final int titanium;
    private final int plants;
    private final int cards;
    private final boolean isWater;
    private final boolean isVolcano;
    private final boolean isNoctis;

    public TileProperties(int steel, int titanium, int plants, int cards, boolean isWater) {
        this(steel, titanium, plants, cards, isWater, false, false);
    }

    public TileProperties(int steel, int titanium, int plants, int cards, boolean isWater, boolean isVolcano, boolean isNoctis) {
        this.steel = steel;
        this.titanium = titanium;
        this.plants = plants;
        this.cards = cards;
        this.isWater = isWater;
        this.isVolcano = isVolcano;
        this.isNoctis = isNoctis;
    }

    public int getSteel() {
        return steel;
    }

    public int getTitanium() {
        return titanium;
    }

    public int getPlants() {
        return plants;
    }

    public boolean isWater() {
        return isWater;
    }

    public boolean isVolcano() {
        return isVolcano;
    }

    public boolean isNoctis() {
        return isNoctis;
    }

    public int getCards() {
        return cards;
    }

    public static final Map<Point, TileProperties> tilePropertiesMap = initMap();

    private static Map<Point, TileProperties> initMap() {
        final Map<Point, TileProperties> map = new HashMap<>();
        map.put(new Point(-4, 4), new TileProperties(2, 0, 0, 0, false));
        map.put(new Point(-3, 4), new TileProperties(2, 0, 0, 0, true));
        map.put(new Point(-1, 4), new TileProperties(0, 0, 0, 1, true));
        map.put(new Point(0, 4), new TileProperties(0, 0, 0, 0, true));
        map.put(new Point(-3, 3), new TileProperties(1, 0, 0, 0, false, true, false));
        map.put(new Point(1, 3), new TileProperties(0, 0, 0, 2, true));
        map.put(new Point(-4, 2), new TileProperties(0, 0, 0, 1, false, true, false));
        map.put(new Point(2, 2), new TileProperties(1, 0, 0, 0, false));
        map.put(new Point(-4, 1), new TileProperties(0, 1, 1, 0, false, true, false));
        map.put(new Point(-3, 1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(-2, 1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(-1, 1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(0, 1), new TileProperties(0, 0, 2, 0, false));
        map.put(new Point(1, 1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(2, 1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(3, 1), new TileProperties(0, 0, 2, 0, true));
        map.put(new Point(-4, 0), new TileProperties(0, 0, 2, 0, false, true, false));
        map.put(new Point(-3, 0), new TileProperties(0, 0, 2, 0, false));
        map.put(new Point(-2, 0), new TileProperties(0, 0, 2, 0, false, false, true));
        map.put(new Point(-1, 0), new TileProperties(0, 0, 2, 0, true));
        map.put(new Point(0, 0), new TileProperties(0, 0, 2, 0, true));
        map.put(new Point(1, 0), new TileProperties(0, 0, 2, 0, true));
        map.put(new Point(2, 0), new TileProperties(0, 0, 2, 0, false));
        map.put(new Point(3, 0), new TileProperties(0, 0, 2, 0, false));
        map.put(new Point(4, 0), new TileProperties(0, 0, 2, 0, false));
        map.put(new Point(-3, -1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(-2, -1), new TileProperties(0, 0, 2, 0, false));
        map.put(new Point(-1, -1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(0, -1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(1, -1), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(2, -1), new TileProperties(0, 0, 1, 0, true));
        map.put(new Point(3, -1), new TileProperties(0, 0, 1, 0, true));
        map.put(new Point(4, -1), new TileProperties(0, 0, 1, 0, true));
        map.put(new Point(3, -2), new TileProperties(0, 0, 1, 0, false));
        map.put(new Point(-1, -3), new TileProperties(2, 0, 0, 0, false));
        map.put(new Point(1, -3), new TileProperties(0, 0, 0, 1, false));
        map.put(new Point(2, -3), new TileProperties(0, 0, 0, 1, false));
        map.put(new Point(4, -3), new TileProperties(0, 1, 0, 0, false));
        map.put(new Point(0, -4), new TileProperties(1, 0, 0, 0, false));
        map.put(new Point(1, -4), new TileProperties(2, 0, 0, 0, false));
        map.put(new Point(4, -4), new TileProperties(0, 2, 0, 0, true));
        return map;
    }
}
