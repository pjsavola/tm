package tm;

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

	public boolean hasEvent() {
		return event > 0;
	}

	public boolean hasBuilding() {
		return building > 0;
	}

	public boolean hasSpace() {
		return space > 0;
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
}
