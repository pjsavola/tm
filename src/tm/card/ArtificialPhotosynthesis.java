package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.CardAction;
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
                return new SelectActionAction.SelectActionCompletable<>(game, Arrays.asList(
                    new CardAction(true, null) {
                        @Override
                        public String getDescription() {
                            return "Gain 1 plant";
                        }
                        @Override
                        public Action getAction(Game game) {
                            return new IncomeDeltaAction(Resources.PLANT);
                        }
                    },
                    new CardAction(true, null) {
                        @Override
                        public String getDescription() {
                            return "Gain 2 energy";
                        }
                        @Override
                        public Action getAction(Game game) {
                            return new IncomeDeltaAction(new Resources(0, 0, 0, 0, 2, 0));
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
