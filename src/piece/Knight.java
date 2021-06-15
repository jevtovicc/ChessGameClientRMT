package piece;

import color.PieceColor;
import gui.GameWindow;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public void move(Position destination) {
        Position position = getPosition();
        position.setPiece(null); // set previous position to null
        position.setIcon(null);
        GameWindow.setPieceAt(this, destination.getColumn(), destination.getRow());
    }

    @Override
    public List<Position> getAvailablePositions() {

        List<Position> positions = new ArrayList<>();

        Position currPosition = getPosition();

        if (currPosition.getRow() + 2 <= 8) {

            if (currPosition.getColumn() - 1 >= 'a') {
                Position position = GameWindow.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() + 2);
                position.getPiece()
                .ifPresentOrElse(curr -> {
                    if (curr.getColor() != getColor()) {
                        positions.add(position);
                    }
                }, () -> positions.add(position));
            }

            if (currPosition.getColumn() + 1 <= 'h') {
                Position position = GameWindow.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() + 2);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                positions.add(position);
                            }
                        }, () -> positions.add(position));
            }

        }

        if (currPosition.getRow() - 2 >= 1) {

            if (currPosition.getColumn() - 1 >= 'a') {
                Position position = GameWindow.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() - 2);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                positions.add(position);
                            }
                        }, () -> positions.add(position));
            }

            if (currPosition.getColumn() + 1 <= 'h') {
                Position position = GameWindow.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() - 2);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                positions.add(position);
                            }
                        }, () -> positions.add(position));
            }

        }

        if (currPosition.getColumn() + 2 <= 'h') {

            if (currPosition.getRow() + 1 <= 8) {
                Position position = GameWindow.getPositionAt((char)(currPosition.getColumn() + 2), currPosition.getRow() + 1);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                positions.add(position);
                            }
                        }, () -> positions.add(position));
            }

            if (currPosition.getRow() - 1 >= 1) {
                Position position = GameWindow.getPositionAt((char)(currPosition.getColumn() + 2), currPosition.getRow() - 1);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                positions.add(position);
                            }
                        }, () -> positions.add(position));
            }

        }

        if (currPosition.getColumn() - 2 >= 'a') {

            if (currPosition.getRow() + 1 <= 8) {
                Position position = GameWindow.getPositionAt((char)(currPosition.getColumn() - 2), currPosition.getRow() + 1);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                positions.add(position);
                            }
                        }, () -> positions.add(position));
            }

            if (currPosition.getRow() - 1 >= 1) {
                Position position = GameWindow.getPositionAt((char)(currPosition.getColumn() - 2), currPosition.getRow() - 1);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                positions.add(position);
                            }
                        }, () -> positions.add(position));
            }

        }

        return positions;
    }
}
