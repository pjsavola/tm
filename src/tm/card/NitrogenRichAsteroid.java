package tm.card;

import java.util.Arrays;
import java.util.List;

import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddTemperatureAction;
import tm.action.AddTerraformingRatingAction;
import tm.completable.Completable;
import tm.completable.InstantCompletable;

public class NitrogenRichAsteroid extends Card {

    public NitrogenRichAsteroid() {
        super("Nitrogen-Rich Asteroid", 31, new Tags().science().event());
    }

    @Override
    public Action getInitialAction(Game game) {
        return new ActionChain(
            new AddTemperatureAction(),
            new AddTerraformingRatingAction(2),
            new Action() {
                @Override
                public Completable begin(Game game) {
                    final int plants;
                    if (game.getCurrentPlayer().hasTags(new Tags().plant().plant().plant())) {
                        plants = 4;
                    } else {
                        plants = 1;
                    }
                    final Resources resources = new Resources(0, 0, 0, plants, 0, 0);
                    return new InstantCompletable(game) {
                        @Override
                        public void complete() {
                            game.getCurrentPlayer().adjustIncome(resources);
                        }

                        @Override
                        public void undo() {
                            game.getCurrentPlayer().adjustIncome(resources.negate());
                        }

                        @Override
                        public void redo() {
                            game.getCurrentPlayer().adjustIncome(resources);
                        }
                    };
                }
            }
        );
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("1 temperature", "2 TR", "1 plant income", "3 more plant income if you have at least 3 plant tags");
    }
}
