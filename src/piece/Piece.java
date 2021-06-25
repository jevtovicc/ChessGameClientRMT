package piece;

import gui.Board;
import gui.GameWindow;
import gui.Position;

import java.util.List;

public abstract class Piece {
    private final PieceColor pieceColor;
    private Position position;
    private final String pngFilePath; // icon
    protected List<Position> availablePositions; // positions where piece can move based on board and piece's current position

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
    public List<Position> getAvailablePositions() { return availablePositions; }

    public void move(Position destination) {
        Position previousPosition = getPosition();
        previousPosition.setPiece(null); // set previous position's piece to null
        previousPosition.setIcon(null); // remove icon from previous position
        GameWindow.getBoardPane().setPieceAt(this, destination.getColumn(), destination.getRow());
    }

    /* return true if move to destination doesn't lead to check */
    public boolean tryMove(Board board, Position destination) {

        /* if piece is pawn, keep track of it's previous moved property */
        boolean moved = false;
        if (this instanceof Pawn) {
            moved = ((Pawn) this).hasMoved();
        }

        boolean valid = true; // keeps track of whether or not this move is valid (doesn't lead to check)

        Position source = getPosition();
        Piece previousPiece = destination.getPiece().orElse(null);

        move(destination);

        if (board.calculateIfInDanger()) {
            valid = false;
        }

        /* reset tried move so it doesn't affect original board */
        move(source);
        if (previousPiece != null) {
            previousPiece.move(destination);
        }

        /* reset pawn's moved property to previous value */
        if (this instanceof Pawn) {
            ((Pawn) this).setMoved(moved);
        }

        return valid;
    }

    /* removes all highlighted borders and clears available positions list */
    public void resetAvailablePositions() {
        availablePositions.forEach(p -> p.setBorder(null));
        availablePositions.clear();
    }

    /* each piece has it's own way of calculating available positions */
    public abstract List<Position> calculateAvailablePositions(Board board);

    @Override
    public String toString() { return getColor() + "-" + getClass().getName(); }
}
