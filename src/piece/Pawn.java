package piece;

import gui.Position;

import java.util.List;

public abstract class Pawn extends Piece {

    private boolean moved;

    public Pawn(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    public abstract List<Position> getAvailablePositions();

    public boolean hasMoved() { return moved; }
    public void setMoved(boolean moved) { this.moved = moved; }
}
