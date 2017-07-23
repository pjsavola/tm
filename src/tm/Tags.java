package tm;

public class Tags {

	private int event;
	private int building;
	private int city;
	private int space;
	private int earth;
	private int jovian;
	private int science;
	private int energy;
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

	public Tags energy() {
		energy++;
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
}
