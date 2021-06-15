package piece;

import color.PieceColor;
import gui.GameWindow;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class WhitePawn extends Pawn {

    public WhitePawn(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public void move(Position destination) {
        super.move(destination);
        setMoved(true);
    }

    @Override
    public List<Position> getAvailablePositions() {

        List<Position> positions = new ArrayList<>();

        Position currPosition = getPosition();

        if (!hasMoved()) {
            positions.add(GameWindow.getPositionAt(currPosition.getColumn(), currPosition.getRow() - 2));
        }

        // straight by one position
        Position destination = GameWindow.getPositionAt(currPosition.getColumn(), currPosition.getRow() - 1);
        if (destination.getPiece().isEmpty()) {
            positions.add(destination);
        }

        // diagonally to eat opposite piece
        if (currPosition.getRow() - 1 >= 1) {
            if (currPosition.getColumn() - 1 >= 'a') {
                Position pos = GameWindow.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() - 1);
                pos.getPiece()
                        .ifPresent(currPiece -> {
                            if (currPiece.getColor() != getColor()) {
                                positions.add(pos);
                            }
                        });
            }
            if (currPosition.getColumn() + 1 <= 'h') {
                Position pos = GameWindow.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() - 1);
                pos.getPiece()
                        .ifPresent(currPiece -> {
                            if (currPiece.getColor() != getColor()) {
                                positions.add(pos);
                            }
                        });
            }
        }


        return positions;
    }
}
