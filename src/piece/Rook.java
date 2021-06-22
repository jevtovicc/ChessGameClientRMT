package piece;

import gui.GameWindow;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public List<Position> calculateAvailablePositions() {

        availablePositions = new ArrayList<>();

        Position currPosition = getPosition();

        int rowIncrement = 1;

        while (currPosition.getRow() + rowIncrement <= 8) {
            Position temp = GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() + rowIncrement);
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
            Position temp = GameWindow.getBoardPane().getPositionAt(currPosition.getColumn(), currPosition.getRow() - rowIncrement);
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
            Position temp = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() + colIncrement), currPosition.getRow());
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
            Position temp = GameWindow.getBoardPane().getPositionAt((char)(currPosition.getColumn() - colIncrement), currPosition.getRow());
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
