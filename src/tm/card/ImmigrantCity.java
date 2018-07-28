package tm.card;

import java.util.Arrays;
import java.util.List;

import com.sun.istack.internal.Nullable;
import tm.Card;
import tm.Game;
import tm.Resources;
import tm.Tags;
import tm.Tile;
import tm.action.Action;
import tm.action.IncomeDeltaAction;
import tm.action.PlaceTileAction;
import tm.effect.PlaceTileEffect;
import tm.effect.PlayCardEffect;

public class ImmigrantCity extends Card implements PlaceTileEffect, PlayCardEffect {

    public ImmigrantCity() {
        super("Immigrant City", 13, Tags.BUILDING_CITY, null, true);
    }

    @Override
    public Resources getIncomeDelta(Game game) {
        return new Resources(-2, 0, 0, 0, -1, 0);
    }

    @Override
    public Action getInitialAction(Game game) {
        return new PlaceTileAction(Tile.Type.CITY);
    }

    @Override
    protected List<String> getContents() {
        return Arrays.asList("Effect:", "1 money income for each city", "(including this)", "-4 money income", "-1 energy income");
    }

    @Nullable
    @Override
    public Action tilePlaced(Tile tile) {
        if (Tile.isCity(tile.getType())) {
            return new IncomeDeltaAction(Resources.MONEY);
        }
        return null;
    }

    @Nullable
    @Override
    public Action cardPlayed(Card card) {
        if (card instanceof PhobosSpaceHaven) {
            return new IncomeDeltaAction(Resources.MONEY);
        } else if (card instanceof GanymedeColony) {
            return new IncomeDeltaAction(Resources.MONEY);
        }
        return null;
    }
}
