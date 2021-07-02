package piece;

import gui.Board;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor color) {
        super(color, "resources/rook-" + (color == PieceColor.Black ? "black" : "white") + ".png");
    }

    @Override
    public List<Position> calculateAvailablePositions(Board board) {

        availablePositions = new ArrayList<>();

        Position currPosition = getPosition();

        int rowIncrement = 1;

        while (currPosition.getRow() + rowIncrement <= 8) {
            Position temp = board.getPositionAt(currPosition.getColumn(), currPosition.getRow() + rowIncrement);
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    availablePositions.add(temp);
                }
                break;
            } else {
                availablePositions.add(temp);
            }
            rowIncrement++;
        }

        rowIncrement = 1;

        while (currPosition.getRow() - rowIncrement >= 1) {
            Position temp = board.getPositionAt(currPosition.getColumn(), currPosition.getRow() - rowIncrement);
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    availablePositions.add(temp);
                }
                break;
            } else {
                availablePositions.add(temp);
            }
            rowIncrement++;
        }

        int colIncrement = 1;

        while (currPosition.getColumn() + colIncrement <= 'h') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() + colIncrement), currPosition.getRow());
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    availablePositions.add(temp);
                }
                break;
            } else {
                availablePositions.add(temp);
            }
            colIncrement++;
        }

        colIncrement = 1;

        while (currPosition.getColumn() - colIncrement >= 'a') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() - colIncrement), currPosition.getRow());
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    availablePositions.add(temp);
                }
                break;
            } else {
                availablePositions.add(temp);
            }
            colIncrement++;
        }

        return availablePositions;
    }
}
