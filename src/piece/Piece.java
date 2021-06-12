package piece;

import board.Move;
import gui.Position;
import color.PieceColor;

import java.util.List;

public abstract class Piece {
    private PieceColor pieceColor;
    private Position position;

    public Piece(PieceColor c) {
        pieceColor = c;
    }

    public void setPosition(Position p) { position = p; }

    public PieceColor getColor() { return pieceColor; }
    public Position getPosition() { return position; }

    public abstract void move();
    public abstract List<Move> getPossibleMoves();
}
