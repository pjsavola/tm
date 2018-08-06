package tm.card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import tm.Card;
import tm.Game;
import tm.Renderer;
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
        super("Nitrogen-Rich Asteroid", 31, Tags.SPACE_EVENT);
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
                    if (game.getCurrentPlayer().getTags().hasAll(Tags.Type.PLANT.createTags(3))) {
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

                @Override
                public Point render(Graphics g, int x, int y, Game game) {
                    final Point p = Resources.PLANT.render(g, x, y, true);
                    g.setColor(Color.WHITE);
                    Renderer.renderText(g, "OR", p.x + 10, y + 4, false);
                    final Point p2 = Tags.Type.PLANT.createTags(3).render(g, x, p.y + 4, true);
                    return Resources.PLANT_4.render(g, p2.x + 4, p.y + 4, true);
                }
            }
        );
    }
}
