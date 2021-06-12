package piece;

import board.Move;
import color.PieceColor;

import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor c) {
        super(c);
    }

    @Override
    public void move() {

    }

    @Override
    public List<Move> getPossibleMoves() {
        return null;
    }
}
