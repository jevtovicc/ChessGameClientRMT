package piece;

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
    public List<Position> calculateAvailablePositions() {

        availablePositions = new ArrayList<>();

        Position currPosition = getPosition();

        if (!hasMoved()) {
            if (GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() + 1).getPiece().isEmpty()
                    && GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() + 2).getPiece().isEmpty())
                availablePositions.add(GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() + 2));
        }

        if (currPosition.getRow() - 1 >= 1) {
            // straight by one position
            Position destination = GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() + 1);
            if (destination.getPiece().isEmpty()) {
                availablePositions.add(destination);
            }

            // diagonally to eat opposite piece
            if (currPosition.getColumn() - 1 >= 'a') {
                // left diagonal
                Position pos = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() + 1);
                pos.getPiece()
                        .ifPresent(currPiece -> {
                            if (currPiece.getColor() != getColor()) {
                                availablePositions.add(pos);
                            }
                        });
            }

            if (currPosition.getColumn() + 1 <= 'h') {
                // right diagonal
                Position pos = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() + 1);
                pos.getPiece()
                        .ifPresent(currPiece -> {
                            if (currPiece.getColor() != getColor()) {
                                availablePositions.add(pos);
                            }
                        });
            }
        }

        return availablePositions;
    }
}
