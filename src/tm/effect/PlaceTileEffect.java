package tm.effect;

import com.sun.istack.internal.Nullable;
import tm.Tile;
import tm.action.Action;

public interface PlaceTileEffect {
    @Nullable
    Action tilePlaced(Tile tile);
}
