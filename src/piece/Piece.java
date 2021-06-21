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

    public boolean tryMove(Position destination) {
        boolean moved = false;
        if (this instanceof Pawn) {
            moved = ((Pawn) this).hasMoved();
        }

        boolean valid = true;

        Position source = getPosition();
        var optionalPiece = destination.getPiece();

        move(destination);

        if (GameWindow.calculateIfInDanger()) {
            valid = false;
        }

        move(source);
        optionalPiece.ifPresent(p -> p.move(destination));

        if (this instanceof Pawn) {
            ((Pawn) this).setMoved(moved);
        }

        return valid;
    }


    public abstract List<Position> getAvailablePositions();

    @Override
    public String toString() { return getColor() + "-" + getClass().getName(); }
}
