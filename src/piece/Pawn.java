package piece;

import gui.Board;
import gui.Position;

import java.util.List;

public abstract class Pawn extends Piece {

    /* if pawn hasn't moved from it's starting position, it can move up to 2 positions */
    private boolean moved;

    public Pawn(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    public abstract List<Position> calculateAvailablePositions(Board board);

    public boolean hasMoved() { return moved; }
    public void setMoved(boolean moved) { this.moved = moved; }
}
