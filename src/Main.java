import solver.Solver;
import structures.Mosaic;
import structures.Piece;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        try {

            Mosaic mosaic = IO.readMosaic();
            ArrayList<Piece> pieces = Solver.solve(mosaic);
            IO.writeResult(pieces);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
