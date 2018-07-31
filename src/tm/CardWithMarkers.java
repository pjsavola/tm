package tm;

import java.awt.Color;
import java.awt.Graphics;

import com.sun.istack.internal.Nullable;
import tm.effect.ScoringEffect;
import tm.requirement.Requirement;

public abstract class CardWithMarkers extends Card implements ScoringEffect {

    private int markers;
    private final int vp;
    private final int mc;

    public CardWithMarkers(String name, int cost, Tags tags, int vp, int mc) {
        this(name, cost, tags, null, vp, mc);
    }

    public CardWithMarkers(String name, int cost, Tags tags, @Nullable Requirement requirement, int vp, int mc) {
        this(name, cost, tags, requirement, false, vp, mc);
    }

    public CardWithMarkers(String name, int cost, Tags tags, @Nullable Requirement requirement, boolean effect, int vp, int mc) {
        super(name, cost, tags, requirement, effect);
        this.vp = vp;
        this.mc = mc;
    }

    public int getMarkerCount() {
        return markers;
    }

    public void adjustMarkers(int delta) {
        markers += delta;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(Color.LIGHT_GRAY);
        final String str = mc == 0 ? ("(" + vp + ")") : (vp + "/" + mc);
        final int width = g.getFontMetrics().stringWidth(str);
        g.drawString(str, x + (16 - width) / 2, y + 12);
    }

    @Override
    public int getVPs(Player player) {
        if (mc == 0 && getMarkerCount() > 0) {
            return vp;
        }
        return vp * getMarkerCount() / mc;
    }
}
