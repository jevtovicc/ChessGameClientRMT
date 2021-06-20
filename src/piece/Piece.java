package piece;

import gui.GameWindow;
import gui.Position;

import java.util.List;

public abstract class Piece {
    private PieceColor pieceColor;
    private Position position;
    private String pngFilePath;

    public enum PieceColor {
        White, Black
    }

    public Piece(PieceColor c, String pngFilePath) {
        pieceColor = c;
        this.pngFilePath = pngFilePath;
    }

    public void setPosition(Position p) { position = p; }

    public PieceColor getColor() { return pieceColor; }
    public Position getPosition() { return position; }
    public String getPngFilePath() { return pngFilePath; }

    public void move(Position destination) {
        Position position = getPosition();
        position.setPiece(null); // set previous position to null
        position.setIcon(null);
        GameWindow.setPieceAt(this, destination.getColumn(), destination.getRow());
    }

    public abstract List<Position> getAvailablePositions();

    @Override
    public String toString() { return getColor() + "-" + getClass().getName(); }
}
