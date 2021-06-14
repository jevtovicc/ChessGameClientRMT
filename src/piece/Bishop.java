package piece;

import board.Move;
import color.PieceColor;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public void move(Move m) {

    }

    @Override
    public List<Move> getPossibleMoves() {
        return null;
    }
}
