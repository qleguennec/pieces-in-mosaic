package structures;

import solver.Solver;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public int getArea() {
        return dimensions.x * dimensions.y;
    }

    public Pair topLeft() {
        return this.dimensions;
    }

    public Pair topRight() {
        return new Pair(positions.x + dimensions.x, positions.y);
    }

    public Pair bottomLeft() {
        return new Pair(positions.x, positions.y + dimensions.y);
    }

    public Pair bottomRight() {
        return new Pair(positions.x + dimensions.x, positions.y + dimensions.y);
    }

    public boolean overlapsWith(Rectangle other) {
        if (this.topRight().y < other.bottomLeft().y
                || this.bottomLeft().y > other.topRight().y) {
            return false;
        }

        if (this.topRight().x < other.bottomLeft().x
                || this.bottomLeft().x > other.topRight().x) {
            return false;
        }

        return true;
    }

    public boolean overlapsWithAny(List<? extends Rectangle> others) {
        return others.stream().anyMatch(this::overlapsWith);
    }

    public List<Piece> breakIntoSubPieces(Mosaic mosaic, int maxArea)  {
        return Solver.getPossiblyOverlappingPieces(mosaic, this, maxArea);
    }
}
