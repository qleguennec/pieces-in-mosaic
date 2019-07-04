package structures;

import solver.Solver;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.*;
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

    public boolean overlapsWith(Rectangle r) {
        return positions.x < r.positions.x + r.dimensions.x
                && positions.x + dimensions.x > r.positions.x
                && positions.y < r.positions.y + r.dimensions.y
                && positions.y + dimensions.y > r.positions.y;
    }

    public boolean overlapsWithAny(HashSet<Rectangle> overlaps, List<? extends Rectangle> others) {
        if (overlaps.contains(this))
            return true;

        Optional<? extends Rectangle> firstOverlap = others
                .stream()
                .filter(r -> !this.equals(r))
                .filter(this::overlapsWith)
                .findFirst();

        firstOverlap.ifPresent(overlap -> {
            overlaps.add(overlap);
            overlaps.add(this);
        });

        return firstOverlap.isPresent();
    }

    public List<Piece> breakIntoSubPieces(Mosaic mosaic, int maxArea) {
        if (maxArea < 2)
            return Collections.emptyList();

        List<Piece> subPieces = Solver.getPossiblyOverlappingPieces(mosaic, this, maxArea);

        if (subPieces.isEmpty())
            return breakIntoSubPieces(mosaic, maxArea - 1);
        else
            return subPieces;
    }
}
