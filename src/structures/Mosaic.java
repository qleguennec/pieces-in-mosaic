package structures;

public class Mosaic extends Rectangle {
    public int minCellsEachColorByPiece;
    public int maxCellsByPiece;
    public Pair<Boolean, Integer>[][] cells;

    private int piecesCommitted;

    public Mosaic(int x, int y, int w, int h) {
        super(x, y, w, h);
        piecesCommitted = 0;
    }

    private Piece getNewPiece(Rectangle area) {
        Piece piece = new Piece(area);
        int nWhites = 0, nBlacks = 0;

        for (int i = area.position.y; i < area.position.y + area.dimension.y; i++) {
            for (int j = area.position.x; j < area.position.x + area.dimension.x; j++) {
                if (cells[i][j].y != null)
                    return null;

                piece.cells.add(new Pair<>(j, i));

                if (this.cells[i][j].x)
                    nWhites++;
                else nBlacks++;
            }
        }

        return nWhites >= minCellsEachColorByPiece && nBlacks >= minCellsEachColorByPiece ? piece : null;
    }

    public Piece commitNewPiece(Rectangle area) {
        Piece newPiece = getNewPiece(area);

        if (newPiece == null)
            return null;

        newPiece.cells
                .forEach(pair -> cells[(int) pair.y][(int) pair.x].y = piecesCommitted);

        piecesCommitted++;
        return newPiece;
    }

    @Override public String toString() {
        return String.format("Mosaic %dx%d with max cells par piece %d and %d min cells of each color",
                dimension.y, dimension.x, maxCellsByPiece, minCellsEachColorByPiece);
    }
}
