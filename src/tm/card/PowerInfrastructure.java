package tm.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    private final Action action = new CardAction(true, ActionType.POWER_INFRASTRUCTURE) {
        @Override
        protected Action getAction(Game game) {
            return new Action() {
                @Override
                public Completable begin(Game game) {
                    final List<Action> actions = new ArrayList<>();
                    final int max = game.getCurrentPlayer().getEnergy();
                    for (int i = 1; i <= max; i++) {
                        final int x = i;
                        actions.add(new ResourceDeltaAction(new Resources(i, 0, 0, 0, -i, 0)) {
                            @Override
                            public String getDescription() {
                                return "" + x + "money, -" + x + " energy";
                            }
                        });
                    }
                    return new SelectActionAction.SelectActionCompletable(game, actions);
                }
            };
        }
    };

    public PowerInfrastructure() {
        super("Power Infrastructure", 4, Tags.BUILDING.combine(Tags.POWER));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(action);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Action:", "X money, -X power");
    }
}
