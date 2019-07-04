package solver;

import structures.Mosaic;
import structures.Pair;
import structures.Piece;
import structures.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class Solver {
    private static final Logger LOGGER = Logger.getLogger(Solver.class.getName());

    public static List<Piece> getPossiblyOverlappingPieces(Mosaic mosaic, Rectangle region, int maxArea) {
        Set<Pair> factors = Maths.findFactors(maxArea);

        List<Piece> possiblyOverlappingPieces = factors.stream()
                .map(pair -> {
                    int w = pair.x;
                    int h = pair.y;
                    ArrayList<Piece> nWhitesBlacks = new ArrayList<>();

                    for (int i = region.positions.y; i + h <= region.dimensions.y; i++) {
                        for (int j = region.positions.x; j + w <= region.dimensions.x; j++) {
                            Piece piece = mosaic.getPieceInArea(new Rectangle(j, i, w, h), mosaic);
                            if (piece != null) {
                                nWhitesBlacks.add(piece);
                            }
                        }
                    }

                    return nWhitesBlacks;
                })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        LOGGER.log(Level.INFO, "Found {0} (possibly overlapping) pieces", possiblyOverlappingPieces.size());
        return possiblyOverlappingPieces;
    }

    public static List<Piece> resolveOverlaps(Mosaic mosaic, List<Piece> pieces) {
        HashSet<Rectangle> overlaps = new HashSet<>();

        return pieces
                .stream()
                .collect(Collectors.partitioningBy(piece -> piece.overlapsWithAny(overlaps, pieces)))
                .entrySet()
                .stream()
                .flatMap(entry -> {
                    if (entry.getKey()) {
                        LOGGER.log(Level.INFO, "Found {0} overlaps", entry.getValue().size());
                        return entry
                                .getValue()
                                .stream()
                                .map(piece -> ((Rectangle) piece)
                                        .breakIntoSubPieces(mosaic, piece.getArea()))
                                .flatMap(List::stream);
                    } else {
                        return entry.getValue().stream();
                    }
                }).collect(Collectors.toList());
    }

    public static List<Piece> solve(Mosaic mosaic) {
        List<Piece> possiblyOverlappingPieces = mosaic.breakIntoSubPieces(mosaic, mosaic.maxCellsByPiece);
        return resolveOverlaps(mosaic, possiblyOverlappingPieces);
    }
}
