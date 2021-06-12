package piece;

import board.Move;
import color.PieceColor;

import java.util.List;

public class King extends Piece {
    public King(PieceColor c) {
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
