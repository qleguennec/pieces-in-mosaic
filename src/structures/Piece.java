package structures;

import java.util.ArrayList;
import java.util.List;

public class Piece extends Rectangle {
    public List<Boolean> cells;

    public Piece(Rectangle area) {
        super(area);
    }
}
