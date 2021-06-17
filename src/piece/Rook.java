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
    public List<Position> getAvailablePositions() {

        List<Position> positions = new ArrayList<>();

        Position currPosition = getPosition();

        int rowIncrement = 1;

        while (currPosition.getRow() + rowIncrement <= 8) {
            Position temp = GameWindow.getPositionAt(currPosition.getColumn(), currPosition.getRow() + rowIncrement);
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    positions.add(temp);
                }
                break;
            } else {
                positions.add(temp);
            }
            rowIncrement++;
        }

        rowIncrement = 1;

        while (currPosition.getRow() - rowIncrement >= 1) {
            Position temp = GameWindow.getPositionAt(currPosition.getColumn(), currPosition.getRow() - rowIncrement);
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    positions.add(temp);
                }
                break;
            } else {
                positions.add(temp);
            }
            rowIncrement++;
        }

        int colIncrement = 1;

        while (currPosition.getColumn() + colIncrement <= 'h') {
            Position temp = GameWindow.getPositionAt((char)(currPosition.getColumn() + colIncrement), currPosition.getRow());
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    positions.add(temp);
                }
                break;
            } else {
                positions.add(temp);
            }
            colIncrement++;
        }

        colIncrement = 1;

        while (currPosition.getColumn() - colIncrement >= 'a') {
            Position temp = GameWindow.getPositionAt((char)(currPosition.getColumn() - colIncrement), currPosition.getRow());
            if (temp.getPiece().isPresent()) {
                if (temp.getPiece().get().getColor() != getColor()) {
                    positions.add(temp);
                }
                break;
            } else {
                positions.add(temp);
            }
            colIncrement++;
        }

        return positions;
    }
}
