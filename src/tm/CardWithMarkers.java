package tm;

import com.sun.istack.internal.Nullable;
import tm.requirement.Requirement;

public abstract class CardWithMarkers extends Card {

    private int markers;

    public CardWithMarkers(String name, int cost, Tags tags) {
        this(name, cost, tags, null);
    }

    public CardWithMarkers(String name, int cost, Tags tags, @Nullable Requirement requirement) {
        this(name, cost, tags, requirement, false);
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

    @Nullable
    public String getRatio() {
        return null;
    }
}
