package piece;

import color.PieceColor;
import gui.Position;

import java.util.List;

public class King extends Piece {
    public King(PieceColor c, String pngFilePath) {
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
