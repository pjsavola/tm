package tm;

public abstract class CardWithMarkers extends Card {

    private int markers;

    public CardWithMarkers(String name, int cost, Tags tags) {
        super(name, cost, tags);
    }

    public CardWithMarkers(String name, int cost, Tags tags, boolean effect) {
        super(name, cost, tags, effect);
    }

    public int getMarkerCount() {
        return markers;
    }

    public void adjustMarkers(int delta) {
        markers += delta;
    }
}
