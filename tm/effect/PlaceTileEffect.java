package tm.effect;

import javax.annotation.Nullable;
import tm.Tile;
import tm.action.Action;

public interface PlaceTileEffect {
    @Nullable
    Action tilePlaced(Tile tile);
}
