package tm.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Player;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.effect.DiscountEffect;

public class QuantumExtractor extends Card implements DiscountEffect {

    public QuantumExtractor() {
        super("Quantum Extractor", 13, Tags.SCIENCE.combine(Tags.POWER), true);
    }

    @Override
    public boolean check(Player player) {
        return player.getTags().hasAll(Tags.Type.SCIENCE.createTags(4));
    }

    @Override
    public Action getInitialAction(Game game) {
        return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 4, 0));
    }

    @Override
    protected List<String> getRequirements() {
        return Collections.singletonList("Requires 4 science tags");
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "Space cards cost 2 less", "", "4 energy income");
    }

    @Override
    public int getDiscount(Card card) {
        if (card.getTags().has(Tags.Type.SPACE)) {
            return 2;
        }
        return 0;
    }
}
