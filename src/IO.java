import structures.Mosaic;
import structures.Pair;
import structures.Piece;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

abstract class IO {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    static Mosaic readMosaic() throws IOException {
        URL url = Main.class.getResource("main/resources/mosaic.in");
        String content = new String(Files.readAllBytes(Paths.get(url.getPath())));
        String[] lines = content.split("\\n");
        String header = lines[0];
        String[] split = header.split("\\s");

        Mosaic mosaic = new Mosaic(0, 0, Integer.parseInt(split[1]), Integer.parseInt(split[0]));
        mosaic.minCellsEachColorByPiece = Integer.parseInt(split[2]);
        mosaic.maxCellsByPiece = Integer.parseInt(split[3]);
        mosaic.cells = new Pair[mosaic.dimension.y][mosaic.dimension.x];

        LOGGER.log(Level.INFO, mosaic.toString());

        for (int i = 0; i < mosaic.dimension.y; i++) {
            for (int j = 0; j < mosaic.dimension.x; j++) {
                mosaic.cells[i][j] = new Pair<>(lines[i + 1].charAt(j) == 'W', null);
            }
        }

        return mosaic;
    }

    static void writeResult(List<Piece> pieces) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Integer.toString(pieces.size()));
        List<String> collect = pieces.stream()
                .map(piece -> Arrays.asList(
                        piece.position.y,
                        piece.position.x,
                        piece.position.y + piece.dimension.y,
                        piece.position.x + piece.dimension.x)
                        .stream()
                        .map(Objects::toString)
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.toList());

        lines.addAll(collect);

        Path outFile = new File("src/main/resources/mosaic.out").toPath();
        Files.write(outFile, lines, StandardCharsets.UTF_8);

        LOGGER.log(Level.INFO, "Wrote result to {0}", outFile.toAbsolutePath());
    }
}
