package piece;

import gui.Board;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(PieceColor color) {
        super(color, "resources/bishop-" + (color == PieceColor.Black ? "black" : "white") + ".png");
    }

    @Override
    public List<Position> calculateAvailablePositions(Board board) {

        availablePositions = new ArrayList<>();

        Position currPosition = getPosition();

        int rowIncrement = 1, colIncrement = 1;

        while (currPosition.getRow() + rowIncrement <= 8 && currPosition.getColumn() + colIncrement <= 'h') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() + colIncrement), currPosition.getRow() + rowIncrement);

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
            Position temp = board.getPositionAt((char)(currPosition.getColumn() - colIncrement), currPosition.getRow() + rowIncrement);

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
            Position temp = board.getPositionAt((char)(currPosition.getColumn() + colIncrement), currPosition.getRow() - rowIncrement);

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
            Position temp = board.getPositionAt((char)(currPosition.getColumn() - colIncrement), currPosition.getRow() - rowIncrement);

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
