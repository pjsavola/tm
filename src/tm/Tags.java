package tm;

import java.awt.Color;
import java.awt.Graphics;

public class Tags {

	private int event;
	private int building;
	private int city;
	private int space;
	private int earth;
	private int jovian;
	private int science;
	private int power;
	private int plant;
	private int animal;
	private int microbe;

	public Tags event() {
		event++;
		return this;
	}

	public Tags building() {
		building++;
		return this;
	}

	public Tags city() {
		city++;
		return this;
	}

	public Tags space() {
		space++;
		return this;
	}

	public Tags earth() {
		earth++;
		return this;
	}

	public Tags jovian() {
		jovian++;
		return this;
	}

	public Tags science() {
		science++;
		return this;
	}

	public Tags power() {
		power++;
		return this;
	}

	public Tags plant() {
		plant++;
		return this;
	}

	public Tags animal() {
		animal++;
		return this;
	}

	public Tags microbe() {
		microbe++;
		return this;
	}

	public Tags combine(Tags tags, boolean add) {
	    if (add) {
            event += tags.event;
            building += tags.building;
            city += tags.city;
            space += tags.space;
            earth += tags.earth;
            jovian += tags.jovian;
            science += tags.science;
            power += tags.power;
            plant += tags.plant;
            animal += tags.animal;
            microbe += tags.microbe;
        } else {
            event -= tags.event;
            building -= tags.building;
            city -= tags.city;
            space -= tags.space;
            earth -= tags.earth;
            jovian -= tags.jovian;
            science -= tags.science;
            power -= tags.power;
            plant -= tags.plant;
            animal -= tags.animal;
            microbe -= tags.microbe;
        }
	    return this;
    }

	public boolean hasEvent() {
		return event > 0;
	}

	public boolean hasBuilding() {
		return building > 0;
	}

	public boolean hasSpace() {
		return space > 0;
	}

	public boolean hasEarth() {
	    return earth > 0;
    }

	public boolean hasJovian() {
		return jovian > 0;
	}

	public boolean hasPower() {
	    return power > 0;
    }

	public void render(Graphics g, int x, int y) {
		int offset = x - 17;
		offset = drawTags(g, "images/tag_event.png", event, offset, y);
		offset = drawTags(g, "images/tag_building.png", building, offset, y);
		offset = drawTags(g, "images/tag_city.png", city, offset, y);
		offset = drawTags(g, "images/tag_space.png", space, offset, y);
		offset = drawTags(g, "images/tag_earth.png", earth, offset, y);
		offset = drawTags(g, "images/tag_jovian.png", jovian, offset, y);
		offset = drawTags(g, "images/tag_science.png", science, offset, y);
		offset = drawTags(g, "images/tag_power.png", power, offset, y);
		offset = drawTags(g, "images/tag_plant.png", plant, offset, y);
		offset = drawTags(g, "images/tag_animal.png", animal, offset, y);
		drawTags(g, "images/tag_microbe.png", microbe, offset, y);
	}

	private static int drawTags(Graphics g, String path, int count, int x, int y) {
		int actualX = x;
		for (int i = 0; i < count; i++) {
			g.drawImage(ImageCache.getImage(path), actualX, y, null);
			actualX -= 17;
		}
		return actualX;
	}

	public void renderVertical(Graphics g, int x, int y) {
        g.setColor(Color.WHITE);
        int offset = y;
        offset = drawTagCount(g, "images/tag_microbe.png", microbe, x, offset);
        offset = drawTagCount(g, "images/tag_animal.png", animal, x, offset);
        offset = drawTagCount(g, "images/tag_plant.png", plant, x, offset);
        offset = drawTagCount(g, "images/tag_power.png", power, x, offset);
        offset = drawTagCount(g, "images/tag_science.png", science, x, offset);
        offset = drawTagCount(g, "images/tag_jovian.png", jovian, x, offset);
        offset = drawTagCount(g, "images/tag_earth.png", earth, x, offset);
        offset = drawTagCount(g, "images/tag_space.png", space, x, offset);
        offset = drawTagCount(g, "images/tag_city.png", city, x, offset);
        offset = drawTagCount(g, "images/tag_building.png", building, x, offset);
        drawTagCount(g, "images/tag_event.png", event, x, offset);
    }

    private static int drawTagCount(Graphics g, String path, int count, int x, int y) {
	    if (count > 0) {
            g.drawImage(ImageCache.getImage(path), x, y, null);
            g.drawString(Integer.toString(count), x + 22, y + 12);
            return y - 18;
        } else {
	        return y;
        }
    }

}
