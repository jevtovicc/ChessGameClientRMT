package piece;

import color.PieceColor;
import gui.Position;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public void move(Position destination) {

    }

    @Override
    public List<Position> getAvailablePositions() {
        return null;
    }
}
