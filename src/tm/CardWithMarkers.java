package tm;

import tm.Card;
import tm.Tags;

public abstract class CardWithMarkers extends Card {

    private int markers;

    public CardWithMarkers(String name, int cost, Tags tags) {
        super(name, cost, tags);
    }

    public int getMarkerCount() {
        return markers;
    }

    public void adjustMarkers(int delta) {
        markers += delta;
    }
}
