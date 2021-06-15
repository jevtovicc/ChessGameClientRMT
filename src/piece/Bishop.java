package piece;

import color.PieceColor;
import gui.Position;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public List<Position> getAvailablePositions() {
        return null;
    }
}
