package tm;

public interface Action {
	char getKey();
	boolean check(Game game);
	Completable begin(Game game);
}
