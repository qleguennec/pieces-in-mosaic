package solver;

import structures.Mosaic;
import structures.Pair;
import structures.Piece;
import structures.Rectangle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Solver {
    public static List<Piece> getPossiblyOverlappingPieces(Mosaic mosaic, Rectangle region, int maxArea) {
        Set<Pair> factors = Maths.findFactors(maxArea);

        return factors.stream()
                .map(pair -> {
                    int w = pair.x;
                    int h = pair.y;
                    ArrayList<Piece> nWhitesBlacks = new ArrayList<>();

                    for (int i = 0; i < region.dimensions.y; i++) {
                        for (int j = 0; j < region.dimensions.x; j++) {
                            Piece piece = mosaic.getPieceInArea(new Rectangle(j, i, w, h));
                            if (piece != null) {
                                nWhitesBlacks.add(piece);
                            }
                        }
                    }

                    return nWhitesBlacks;
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static List<Piece> resolveOverlaps(Mosaic mosaic, List<Piece> pieces) {
        return pieces
                .stream()
                .collect(Collectors.partitioningBy(piece -> piece.overlapsWithAny(pieces)))
                .entrySet()
                .stream()
                .flatMap(entry -> {
                    if (entry.getKey()) {
                        return entry
                                .getValue()
                                .stream()
                                .map(piece -> ((Rectangle) piece).breakIntoSubPieces(mosaic))
                                .flatMap(List::stream);
                    }
                    else {
                        return entry.getValue().stream();
                    }
                }).collect(Collectors.toList());
    }

    public static List<Piece> solve(Mosaic mosaic) {
        List<Piece> possiblyOverlappingPieces = getPossiblyOverlappingPieces(mosaic, mosaic, mosaic.maxCellsByPiece);
        return resolveOverlaps(mosaic, possiblyOverlappingPieces);
    }
}
