package tm;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class TileProperties {
	private final Resources resources;
	private final int cards;
	private final boolean isWater;
	private final boolean isNoctis;
	
	public TileProperties(final int steel, final int titanium, final int plants, final int cards, final boolean isWater, final boolean isNoctis) {
		resources = new Resources(0, steel, titanium, plants, 0, 0);
		this.cards = cards;
		this.isWater = isWater;
		this.isNoctis = isNoctis;
	}

	public Resources getResources() {
		return resources;
	}

	public boolean isWater() {
		return isWater;
	}

	public boolean isNoctis() {
		return isNoctis;
	}

	public int getCards() { return cards; }

	public static final Map<Point, TileProperties> tilePropertiesMap = initMap(); 
	
	private static Map<Point, TileProperties> initMap() {
		final Map<Point, TileProperties> map = new HashMap<>();
		map.put(new Point(-4, 4), new TileProperties(2, 0, 0, 0, false, false));
		map.put(new Point(-3, 4), new TileProperties(2, 0, 0, 0, true, false));
		map.put(new Point(-1, 4), new TileProperties(0, 0, 0, 1, true, false));
		map.put(new Point(0, 4), new TileProperties(0, 0, 0, 0, true, false));
		map.put(new Point(-3, 3), new TileProperties(1, 0, 0, 0, false, false));
		map.put(new Point(1, 3), new TileProperties(0, 0, 0, 2, true, false));
		map.put(new Point(-4, 2), new TileProperties(0, 0, 0, 1, false, false));
		map.put(new Point(2, 2), new TileProperties(1, 0, 0, 0, false, false));
		map.put(new Point(-4, 1), new TileProperties(0, 1, 1, 0, false, false));
		map.put(new Point(-3, 1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(-2, 1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(-1, 1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(0, 1), new TileProperties(0, 0, 2, 0, false, false));
		map.put(new Point(1, 1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(2, 1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(3, 1), new TileProperties(0, 0, 2, 0, true, false));
		map.put(new Point(-4, 0), new TileProperties(0, 0, 2, 0, false, false));
		map.put(new Point(-3, 0), new TileProperties(0, 0, 2, 0, false, false));
		map.put(new Point(-2, 0), new TileProperties(0, 0, 2, 0, false, true));
		map.put(new Point(-1, 0), new TileProperties(0, 0, 2, 0, true, false));
		map.put(new Point(0, 0), new TileProperties(0, 0, 2, 0, true, false));
		map.put(new Point(1, 0), new TileProperties(0, 0, 2, 0, true, false));
		map.put(new Point(2, 0), new TileProperties(0, 0, 2, 0, false, false));
		map.put(new Point(3, 0), new TileProperties(0, 0, 2, 0, false, false));
		map.put(new Point(4, 0), new TileProperties(0, 0, 2, 0, false, false));
		map.put(new Point(-3, -1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(-2, -1), new TileProperties(0, 0, 2, 0, false, false));
		map.put(new Point(-1, -1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(0, -1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(1, -1), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(2, -1), new TileProperties(0, 0, 1, 0, true, false));
		map.put(new Point(3, -1), new TileProperties(0, 0, 1, 0, true, false));
		map.put(new Point(4, -1), new TileProperties(0, 0, 1, 0, true, false));
		map.put(new Point(3, -2), new TileProperties(0, 0, 1, 0, false, false));
		map.put(new Point(-1, -3), new TileProperties(2, 0, 0, 0, false, false));
		map.put(new Point(1, -3), new TileProperties(0, 0, 0, 1, false, false));
		map.put(new Point(2, -3), new TileProperties(0, 0, 0, 1, false, false));
		map.put(new Point(4, -3), new TileProperties(0, 1, 0, 0, false, false));
		map.put(new Point(0, -4), new TileProperties(1, 0, 0, 0, false, false));
		map.put(new Point(1, -4), new TileProperties(2, 0, 0, 0, false, false));
		map.put(new Point(4, -4), new TileProperties(0, 2, 0, 0, true, false));
		return map;
	}
}
