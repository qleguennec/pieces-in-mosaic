import solver.Solver;
import structures.Mosaic;
import structures.Pair;
import structures.Piece;
import structures.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static Mosaic readMosaic() throws IOException {
        URL url = Main.class.getResource("main/resources/mosaic.in");
        String content = new String(Files.readAllBytes(Paths.get(url.getPath())));
        String[] lines = content.split("\\n");
        String header = lines[0];
        String[] split = header.split("\\s");

        Mosaic mosaic = new Mosaic(0, 0, Integer.parseInt(split[1]), Integer.parseInt(split[0]));
        mosaic.minCellsEachColorByPiece = Integer.parseInt(split[2]);
        mosaic.maxCellsByPiece = Integer.parseInt(split[3]);
        mosaic.cells = new boolean[mosaic.dimensions.y][mosaic.dimensions.x];

        LOGGER.log(Level.INFO, mosaic.toString());

        for (int i = 0; i < mosaic.dimensions.y; i++) {
            for (int j = 0; j < mosaic.dimensions.x; j++) {
                mosaic.cells[i][j] = lines[i + 1].charAt(j) == 'W';
            }
        }

        return mosaic;
    }

    public static void main(String[] args) {
        Mosaic mosaic;

        try {
            mosaic = readMosaic();
        } catch (IOException e) {
            e.printStackTrace();
            return ;
        }

        List<Piece> pieces = Solver.solve(mosaic);

        int a = 0;
    }
}
