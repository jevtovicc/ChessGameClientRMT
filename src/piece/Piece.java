package piece;

import board.Move;
import gui.Position;
import color.PieceColor;

import java.util.List;

public abstract class Piece {
    private PieceColor pieceColor;
    private Position position;
    private String pngFilePath;

    public Piece(PieceColor c, String pngFilePath) {
        pieceColor = c;
        this.pngFilePath = pngFilePath;
    }

    public void setPosition(Position p) { position = p; }

    public PieceColor getColor() { return pieceColor; }
    public Position getPosition() { return position; }
    public String getPngFilePath() { return pngFilePath; }

    public abstract void move(Move m);
    public abstract List<Move> getPossibleMoves();
}
