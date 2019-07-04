package structures;

import java.util.ArrayList;

public class Mosaic extends Rectangle {
    public int minCellsEachColorByPiece;
    public int maxCellsByPiece;
    public boolean[][] cells;

    public Mosaic(int x, int y, int w, int h) {
        super(x, y, w, h);
    }


    public boolean checkValid(Piece piece) {
        long nWhites = piece.cells.stream().filter(Boolean::booleanValue).count();
        long nBlacks = piece.cells.size() - nWhites;

        return (nWhites >= minCellsEachColorByPiece && nBlacks >= minCellsEachColorByPiece);
    }


    public Piece getPieceInArea(Rectangle area) {
        Piece piece = new Piece(area);
        piece.cells = new ArrayList<>();

        for (int i = piece.positions.y; i < piece.dimensions.y; i++) {
            for (int j = piece.positions.x; j < piece.dimensions.x; j++)
                piece.cells.add(cells[i][j]);
        }

        if (checkValid(piece))
            return piece;

        return null;
    }
}
