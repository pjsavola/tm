package tm.effect;

public interface ValueEffect {

    default int getSteelDelta() {
        return 0;
    }

    default int getTitaniumDelta() {
        return 0;
    }
}
