package tm.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
import tm.action.ResourceDeltaAction;
import tm.action.SelectActionAction;
import tm.completable.Completable;

public class PowerInfrastructure extends Card {

    private final CardAction action = new CardAction(true, ActionType.POWER_INFRASTRUCTURE) {
        @Override
        protected Action getAction(Game game) {
            return new Action() {
                @Override
                public Completable begin(Game game) {
                    final List<CardAction> actions = new ArrayList<>();
                    final int max = game.getCurrentPlayer().getEnergy();
                    for (int i = 1; i <= max; i++) {
                        final int x = i;
                        actions.add(new CardAction(true, null) {
                            @Override
                            public String getDescription() {
                                return "" + x + "money, -" + x + " energy";
                            }
                            @Override
                            public Action getAction(Game game) {
                                return new ResourceDeltaAction(new Resources(x, 0, 0, 0, -x, 0));
                            }
                        });
                    }
                    final Map<CardAction, Card> cardMap = new HashMap<>();
                    actions.forEach(cardAction -> cardMap.put(cardAction, PowerInfrastructure.this));
                    return new SelectActionAction.SelectActionCompletable<>(game, actions, cardMap);
                }
            };
        }
    };

    public PowerInfrastructure() {
        super("Power Infrastructure", 4, Tags.BUILDING_POWER);
    }

    @Override
    public List<CardAction> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "X money, -X power");
    }
}
