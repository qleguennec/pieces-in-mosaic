package structures;

public class Pair<U, V> {
    public U x;
    public V y;

    public Pair(U x, V y) {
        this.x = x;
        this.y = y;
    }

    Pair(Pair<U, V> pair) {
        this.x = pair.x;
        this.y = pair.y;
    }
}
