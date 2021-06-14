package piece;

import board.Move;
import color.PieceColor;

import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor c, String pngFilePath) {
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
