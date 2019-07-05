package solver;

import structures.Mosaic;
import structures.Pair;
import structures.Piece;
import structures.Rectangle;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class Solver {
    private static final Logger LOGGER = Logger.getLogger(Solver.class.getName());

    private static ArrayList<Piece> tryForDimensions(Mosaic mosaic, Pair<Integer, Integer> dimension) {
        ArrayList<Piece> pieces = new ArrayList<>();
        Piece piece;

        for (int i = 0; i + dimension.y <= mosaic.dimension.y; i++) {
            for (int j = 0; j + dimension.x <= mosaic.dimension.x; j++) {
                piece = mosaic.commitNewPiece(new Rectangle(j, i, dimension.x, dimension.y));
                if (piece != null)
                    pieces.add(piece);
            }
        }

        return pieces;
    }

    public static ArrayList<Piece> solve(Mosaic mosaic) {
        int piecesArea = mosaic.maxCellsByPiece;

        ArrayList<Piece> totalPieces = new ArrayList<>();

        while (piecesArea >= 2) {
            List<Pair<Integer, Integer>> factors = Math.findFactors(piecesArea);

            List<Piece> commitedPieces = factors.stream()
                    .map(factor -> tryForDimensions(mosaic, factor))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            LOGGER.info(String.format("Commited %d pieces of area %s", commitedPieces.size(),
                    factors.get(0).x * factors.get(0).y));

            totalPieces.addAll(commitedPieces);
            piecesArea--;
        }

        return totalPieces;
    }
}
