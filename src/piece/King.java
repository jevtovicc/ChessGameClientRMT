package piece;

import color.PieceColor;
import gui.GameWindow;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public List<Position> getAvailablePositions() {
        List<Position> positions = new ArrayList<>();

        Position currPosition = getPosition();

        if (currPosition.getRow() + 1 <= 8) {
            Position temp = GameWindow.getPositionAt(currPosition.getColumn(), currPosition.getRow() + 1);
            temp.getPiece()
                .ifPresentOrElse(piece -> {
                    if (piece.getColor() != getColor()) {
                        positions.add(temp);
                    }
                }, () -> positions.add(temp));
        }

        if (currPosition.getRow() - 1 >= 1) {
            Position temp = GameWindow.getPositionAt(currPosition.getColumn(), currPosition.getRow() - 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            positions.add(temp);
                        }
                    }, () -> positions.add(temp));
        }

        if (currPosition.getColumn() + 1 <= 'h') {
            Position temp = GameWindow.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow());
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            positions.add(temp);
                        }
                    }, () -> positions.add(temp));
        }

        if (currPosition.getColumn() - 1 >= 'a') {
            Position temp = GameWindow.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow());
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            positions.add(temp);
                        }
                    }, () -> positions.add(temp));
        }

        if (currPosition.getRow() + 1 <= 8 && currPosition.getColumn() + 1 <= 'h') {
            Position temp = GameWindow.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() + 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            positions.add(temp);
                        }
                    }, () -> positions.add(temp));
        }

        if (currPosition.getRow() + 1 <= 8 && currPosition.getColumn() - 1 >= 'a') {
            Position temp = GameWindow.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() + 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            positions.add(temp);
                        }
                    }, () -> positions.add(temp));
        }

        if (currPosition.getRow() - 1 >= 1 && currPosition.getColumn() + 1 <= 'h') {
            Position temp = GameWindow.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() - 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            positions.add(temp);
                        }
                    }, () -> positions.add(temp));
        }

        if (currPosition.getRow() - 1 >= 1 && currPosition.getColumn() - 1 >= 'a') {
            Position temp = GameWindow.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() - 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            positions.add(temp);
                        }
                    }, () -> positions.add(temp));
        }

        return positions;
    }
}
