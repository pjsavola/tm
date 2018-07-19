package tm.corporation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tm.ActionType;
import tm.Corporation;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.ResourceDeltaAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class UnitedNationsMarsInitiative extends Corporation {

    public UnitedNationsMarsInitiative() {
        super("United Nations Mars Initiative", new Tags().plant());
    }

    @Override
    public Action getInitialAction() {
       	return new ResourceDeltaAction(new Resources(40));
    }

    @Override
    public List<Action> getActions() {
        return Collections.singletonList(new ActionChain(ActionType.TR, "Increase TR",
            new ResourceDeltaAction(new Resources(-3)),
            new Action() {
                @Override
                public boolean check(Game game) {
                    return game.getCurrentPlayer().hasIncreasedRating();
                }

                @Override
                public Completable begin(Game game) {
                    return new InstantCompletable(game) {
                        @Override
                        public void complete() {
                            game.getCurrentPlayer().adjustRating(1);
                        }

                        @Override
                        public void undo() {
                            game.getCurrentPlayer().adjustRating(-1);
                        }

                        @Override
                        public void redo() {
                            game.getCurrentPlayer().adjustRating(1);
                        }
                    };
                }
            }
        ));
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("40 money", "May increase TR for 3 money if TR has been increased this turn");
    }
}
