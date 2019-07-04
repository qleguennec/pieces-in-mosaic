package structures;

import java.util.ArrayList;
import java.util.List;

public class Piece extends Rectangle {
    List<Pair> cells;

    Piece(Rectangle area) {
        super(area);
        cells = new ArrayList<>();
    }
}
