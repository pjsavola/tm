package tm.card;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import tm.Card;
import tm.Game;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.completable.Completable;

public class LandClaim extends Card {

    public LandClaim() {
        super("Land Claim", 1, Tags.EVENT);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new Action() {
            @Override
            public Completable begin(Game game) {
                return new PlaceMarkerCompletable(game);
            }
        };
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Place your marker on non-reserved area", "Only you may place tile there");
    }

    private static class PlaceMarkerCompletable implements Completable {
        final Game game;
        private Tile targetTile;
        final MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                targetTile = game.getClosestTile(arg0.getX(), arg0.getY());
                if (targetTile != null) {
                    if (targetTile.getType() != null) {
                        System.err.println("There's already a tile");
                        return;
                    }
                    final boolean isNoctis = targetTile.getProperties() != null && targetTile.getProperties().isNoctis();
                    if (isNoctis) {
                        System.err.println("Reserved for Noctis City");
                        return;
                    }
                    game.getActionHandler().completed(PlaceMarkerCompletable.this);
                }
            }
        };

        public PlaceMarkerCompletable(Game game) {
            this.game = game;
            game.addMouseListener(mouseListener);
        }

        @Override
        public void complete() {
            targetTile.setOwner(game.getCurrentPlayer());
            cancel();
        }

        @Override
        public void undo() {
            targetTile.setOwner(null);
        }

        @Override
        public void redo() {
            targetTile.setOwner(game.getCurrentPlayer());
        }

        @Override
        public boolean remove(Set<Completable> completedSet) {
            return completedSet.remove(this);
        }

        @Override
        public void cancel() {
            game.removeMouseListener(mouseListener);
            game.repaint();
        }
    }
}

