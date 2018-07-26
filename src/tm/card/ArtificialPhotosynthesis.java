package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.ActionType;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.action.SelectActionAction;
import tm.completable.Completable;

public class ArtificialPhotosynthesis extends Card {

    public ArtificialPhotosynthesis() {
        super("Artificial Photosynthesis", 12, Tags.SCIENCE);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new Action() {
            @Override
            public Completable begin(Game game) {
                return new SelectActionAction.SelectActionCompletable(game, Arrays.asList(
                    new IncomeDeltaAction(Resources.PLANT) {
                        @Override
                        public ActionType getType() {
                            return ActionType.ARTIFICIAL_PHOTOSYNTHESIS_1;
                        }
                    },
                    new IncomeDeltaAction(new Resources(0, 0, 0, 0, 2, 0)) {
                        @Override
                        public ActionType getType() {
                            return ActionType.ARTIFICIAL_PHOTOSYNTHESIS_2;
                        }
                    }
                ));
            }
        };
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant income / ", "2 energy income");
    }
}
