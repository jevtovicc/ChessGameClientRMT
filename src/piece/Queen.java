package piece;

import board.Move;
import color.PieceColor;

import java.util.List;

public class Queen extends Piece {
    public Queen(PieceColor c, String pngFilePath) {
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
