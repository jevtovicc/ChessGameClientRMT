package piece;

import gui.GameWindow;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class BlackPawn extends Pawn {

    public BlackPawn(PieceColor c, String pngFilePath) {
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
            if (GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() - 1).getPiece().isEmpty()
                    && GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() - 2).getPiece().isEmpty())
                availablePositions.add(GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() - 2));
        }

        if (currPosition.getRow() + 1 <= 8) {
            // straight by one position
            Position destination = GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() - 1);

            if (destination.getPiece().isEmpty()) {
                availablePositions.add(destination);
            }

            if (currPosition.getColumn() - 1 >= 'a') {
                Position pos = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() - 1);
                pos.getPiece()
                        .ifPresent(currPiece -> {
                            if (currPiece.getColor() != getColor()) {
                                availablePositions.add(pos);
                            }
                        });
            }
            if (currPosition.getColumn() + 1 <= 'h') {
                Position pos = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() - 1);
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
