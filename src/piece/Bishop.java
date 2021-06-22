package piece;

import gui.GameWindow;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public List<Position> calculateAvailablePositions() {

        availablePositions = new ArrayList<>();

        Position currPosition = getPosition();

        int rowIncrement = 1, colIncrement = 1;

        while (currPosition.getRow() + rowIncrement <= 8 && currPosition.getColumn() + colIncrement <= 'h') {
            Position temp = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() + colIncrement), currPosition.getRow() + rowIncrement);

            // break if obstacle piece is found
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    availablePositions.add(temp);
                }
                break;
            } else {
                availablePositions.add(temp);
            }

            rowIncrement++;
            colIncrement++;
        }

        rowIncrement = 1;
        colIncrement = 1;

        while (currPosition.getRow() + rowIncrement <= 8 && currPosition.getColumn() - colIncrement >= 'a') {
            Position temp = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() - colIncrement), currPosition.getRow() + rowIncrement);

            // break if obstacle piece is found
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    availablePositions.add(temp);
                }
                break;
            } else {
                availablePositions.add(temp);
            }

            rowIncrement++;
            colIncrement++;
        }

        rowIncrement = 1;
        colIncrement = 1;

        while (currPosition.getRow() - rowIncrement >= 1 && currPosition.getColumn() + colIncrement <= 'h') {
            Position temp = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() + colIncrement), currPosition.getRow() - rowIncrement);

            // break if obstacle piece is found
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    availablePositions.add(temp);
                }
                break;
            } else {
                availablePositions.add(temp);
            }

            rowIncrement++;
            colIncrement++;
        }

        rowIncrement = 1;
        colIncrement = 1;

        while (currPosition.getRow() - rowIncrement >= 1 && currPosition.getColumn() - colIncrement >= 'a') {
            Position temp = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() - colIncrement), currPosition.getRow() - rowIncrement);

            // break if obstacle piece is found
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    availablePositions.add(temp);
                }
                break;
            } else {
                availablePositions.add(temp);
            }

            rowIncrement++;
            colIncrement++;
        }

        return availablePositions;
    }
}
