package tm;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.action.Action;
import tm.action.ActionChain;
import tm.action.AddOxygenAction;
import tm.action.AddTemperatureAction;
import tm.action.AddWaterAction;
import tm.action.DiscardAction;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.action.PlayCardAction;
import tm.action.ResourceDeltaAction;
import tm.action.SelectActionAction;
import tm.action.SwitchRoundAction;
import tm.card.StandardTechnology;
import tm.completable.Completable;
import tm.corporation.Ecoline;
import tm.corporation.Thorgate;
import tm.standard.StandardAction;

public class ActionPool {
    private static final Font font = new Font("Arial", Font.BOLD, 12);
    private final Game game;
    private final List<StandardAction> standardActions = new ArrayList<>();
    private static final Color ENABLED_COLOR = new Color(0xFFFFFF);
    private static final Color DISABLED_COLOR = new Color(0x666666);

    public ActionPool(Game game) {
        this.game = game;
        standardActions.add(new StandardAction("Sell patents", ActionType.DISCARD) {
            @Override
            public Action getInitialAction(Game game) {
                return new DiscardAction();
            }
        });
        standardActions.add(new StandardAction("Power plant", ActionType.ENERGY) {
            @Override
            public Action getInitialAction(Game game) {
                int cost = -11;
                if (game.getCurrentPlayer().getCorporation() instanceof Thorgate) {
                    cost += 3;
                }
                int reward = 0;
                if (game.getCurrentPlayer().getPlayedCards().stream().anyMatch(card -> card instanceof StandardTechnology)) {
                    reward += 3;
                }
                return new ActionChain(
                    new ResourceDeltaAction(new Resources(cost)),
                    new IncomeDeltaAction(Resources.ENERGY),
                    new ResourceDeltaAction(new Resources(reward))
                );
            }
        });
        standardActions.add(new StandardAction("Asteroid", ActionType.TEMPERATURE) {
            @Override
            public Action getInitialAction(Game game) {
                int reward = 0;
                if (game.getCurrentPlayer().getPlayedCards().stream().anyMatch(card -> card instanceof StandardTechnology)) {
                    reward += 3;
                }
                return new ActionChain(
                    new ResourceDeltaAction(new Resources(-14)),
                    new AddTemperatureAction(),
                    new ResourceDeltaAction(new Resources(reward))
                );
            }
        });
        standardActions.add(new StandardAction("Aquifer", ActionType.WATER) {
            @Override
            public Action getInitialAction(Game game) {
                int reward = 0;
                if (game.getCurrentPlayer().getPlayedCards().stream().anyMatch(card -> card instanceof StandardTechnology)) {
                    reward += 3;
                }
                return new ActionChain(
                    new ResourceDeltaAction(new Resources(-18)),
                    new AddWaterAction(),
                    new ResourceDeltaAction(new Resources(reward))
                );
            }
        });
        standardActions.add(new StandardAction("Greenery", ActionType.GREENERY) {
            @Override
            public Action getInitialAction(Game game) {
                int reward = 0;
                if (game.getCurrentPlayer().getCorporation() instanceof Thorgate) {
                    reward += 4;
                }
                if (game.getCurrentPlayer().getPlayedCards().stream().anyMatch(card -> card instanceof StandardTechnology)) {
                    reward += 3;
                }
                return new ActionChain(
                    new ResourceDeltaAction(new Resources(-23)),
                    new PlaceTileAction(Tile.Type.GREENERY),
                    new AddOxygenAction(),
                    new ResourceDeltaAction(new Resources(reward))
                );
            }
        });
        standardActions.add(new StandardAction("City", ActionType.CITY) {
            @Override
            public Action getInitialAction(Game game) {
                int reward = 0;
                if (game.getCurrentPlayer().getCorporation() instanceof Thorgate) {
                    reward += 4;
                }
                if (game.getCurrentPlayer().getPlayedCards().stream().anyMatch(card -> card instanceof StandardTechnology)) {
                    reward += 3;
                }
                return new ActionChain(
                    new ResourceDeltaAction(new Resources(-25)),
                    new PlaceTileAction(Tile.Type.CITY),
                    new IncomeDeltaAction(Resources.MONEY),
                    new ResourceDeltaAction(new Resources(reward))
                );
            }
        });
        standardActions.add(new StandardAction("Plant", ActionType.PLANT_TO_GREENERY) {
            @Override
            public Action getInitialAction(Game game) {
                int cost = -8;
                if (game.getCurrentPlayer().getCorporation() instanceof Ecoline) {
                    cost++;
                }
                return new ActionChain(
                    new ResourceDeltaAction(new Resources(0, 0, 0, cost, 0, 0)),
                    new PlaceTileAction(Tile.Type.GREENERY),
                    new AddOxygenAction()
                );
            }
        });
        standardActions.add(new StandardAction("Heat", ActionType.HEAT_TO_TEMPERATURE) {
            @Override
            public Action getInitialAction(Game game) {
                return new ActionChain(
                    new ResourceDeltaAction(new Resources(0, 0, 0, 0, 0, -8)),
                    new AddTemperatureAction()
                );
            }
        });
        standardActions.add(new StandardAction("Pass", ActionType.PASS) {
            @Override
            public Action getInitialAction(Game game) {
                return new SwitchRoundAction();
            }
        });
        standardActions.add(new StandardAction("Play card", ActionType.PLAY) {
            @Override
            public Action getInitialAction(Game game) {
                return new PlayCardAction();
            }
        });
        standardActions.add(new StandardAction("Select action", ActionType.CUSTOM) {
            @Override
            public Action getInitialAction(Game game) {
                return new SelectActionAction();
            }
        });
    }

    @Nullable
    public Completable getCompletable(@Nullable ActionType type) {
        for (Action action : game.getCurrentPlayer().getActions()) {
            if (action.getType() == type && action.check(game)) {
                return action.begin(game);
            }
        }
        for (StandardAction standardAction : standardActions) {
            if (standardAction.getType() == type) {
                final Action action = standardAction.getInitialAction(game);
                if (action.check(game)) {
                    return action.begin(game);
                }
            }
        }
        return null;
    }

    public void render(Graphics g) {
        final Color oldColor = g.getColor();
        final boolean canAct = game.getActionHandler().canAct();
        g.setFont(font);
        int i = 13;
        g.setColor(game.getActionHandler().canUndo() ? ENABLED_COLOR : DISABLED_COLOR);
        renderText(g, "u: Undo", 2);
        g.setColor(game.getActionHandler().canRedo() ? ENABLED_COLOR : DISABLED_COLOR);
        renderText(g, "r: Redo", 1);
        g.setColor(oldColor);
    }

    private static void renderText(Graphics g, String text, int i) {
        final FontMetrics metrics = g.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        g.drawString(text, 698 - w, 698 - h * i);
    }
}
