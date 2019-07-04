package structures;

public class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pair(Pair pair) {
        this.x = pair.x;
        this.y = pair.y;
    }

    public Pair getReversed() {
        return new Pair(y, x);
    }
}
