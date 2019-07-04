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

    private static ArrayList<Piece> tryForDimensions(Mosaic mosaic, Pair<Integer, Integer> dimensions) {
        ArrayList<Piece> pieces = new ArrayList<>();
        Piece piece;

        for (int i = 0; i + dimensions.y <= mosaic.dimension.y; i++) {
            for (int j = 0; j + dimensions.x <= mosaic.dimension.x; j++) {
                piece = mosaic.commitNewPiece(new Rectangle(j, i, dimensions.x, dimensions.y));
                if (piece != null)
                    pieces.add(piece);
            }
        }

        return pieces;
    }

    public static ArrayList<Piece> solve(Mosaic mosaic) {
        int piecesArea = mosaic.maxCellsByPiece;
        int commited = 0;

        ArrayList<Piece> totalPieces = new ArrayList<>();

        while (piecesArea >= 2) {
            List<Pair<Integer, Integer>> factors = Math.findFactors(piecesArea);

            List<Piece> commitedPieces = factors.stream()
                    .map(factor -> tryForDimensions(mosaic, factor))
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            commited += commitedPieces.size();

            LOGGER.info(String.format("Commited %d pieces of area %s", commited, factors.get(0).x * factors.get(0).y));

            totalPieces.addAll(commitedPieces);
            piecesArea--;
        }

        return totalPieces;
    }
}
