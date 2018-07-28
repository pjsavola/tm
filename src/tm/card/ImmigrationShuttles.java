package tm.card;

import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Cards;
import tm.Game;
import tm.ImageCache;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.effect.ScoringEffect;

public class ImmigrationShuttles extends Card implements ScoringEffect {

    public ImmigrationShuttles() {
        super("Immigration Shuttles", 31, Tags.SPACE.combine(Tags.EARTH));
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(5);
    }

    @Override
    protected List<String> getContents() {
        return Collections.singletonList("1 vp for each 3 cities");
    }

    @Override
    public int getVPs(Player player) {
        int cityCount = player.getCityCount();
        if (Cards.PHOBOS_SPACE_HAVEN.getOwner() != null) {
            cityCount++;
        }
        if (Cards.GANYMEDE_COLONY.getOwner() != null) {
            cityCount++;
        }
        return cityCount / 3;
    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.drawImage(ImageCache.getImage("images/tag_city.png"), x, y, null);
    }
}
