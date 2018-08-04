package tm.card;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                final List<CardAction> actions = Arrays.asList(
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
                );
                final Map<CardAction, Card> cardMap = new HashMap<>();
                actions.forEach(cardAction -> cardMap.put(cardAction, ArtificialPhotosynthesis.this));
                return new SelectActionAction.SelectActionCompletable<>(game, actions, cardMap);
            }
        };
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 plant income / ", "2 energy income");
    }
}
