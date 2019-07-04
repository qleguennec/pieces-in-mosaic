package structures;

import java.util.List;

public class Rectangle {
    public Pair positions;
    public Pair dimensions;

    public Rectangle(int x, int y, int w, int h) {
        positions = new Pair(x, y);
        dimensions = new Pair(w, h);
    }

    public Rectangle(Rectangle area) {
        positions = new Pair(area.positions);
        dimensions = new Pair(area.dimensions);
    }
}
