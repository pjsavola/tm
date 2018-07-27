package tm;

import com.sun.istack.internal.Nullable;
import tm.requirement.Requirement;

public abstract class CardWithMarkers extends Card {

    private int markers;

    public CardWithMarkers(String name, int cost, Tags tags) {
        super(name, cost, tags);
    }

    public CardWithMarkers(String name, int cost, Tags tags, @Nullable Requirement requirement) {
        super(name, cost, tags, requirement, false);
    }

    public CardWithMarkers(String name, int cost, Tags tags, @Nullable Requirement requirement, boolean effect) {
        super(name, cost, tags, requirement, effect);
    }

    public int getMarkerCount() {
        return markers;
    }

    public void adjustMarkers(int delta) {
        markers += delta;
    }
}
