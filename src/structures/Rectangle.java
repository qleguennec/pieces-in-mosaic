package structures;

public class Rectangle {
    public Pair<Integer, Integer> position;
    public Pair<Integer, Integer> dimension;

    public Rectangle(Integer x, Integer y, Integer w, Integer h) {
        position = new Pair<>(x, y);
        dimension = new Pair<>(w, h);
    }

    Rectangle(Rectangle area) {
        position = new Pair<>(area.position);
        dimension = new Pair<>(area.dimension);
    }

    public int getArea() {
        return dimension.x * dimension.y;
    }

}
