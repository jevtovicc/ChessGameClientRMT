package piece;

import gui.Board;
import gui.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor c, String pngFilePath) {
        super(c, pngFilePath);
    }

    @Override
    public List<Position> calculateAvailablePositions(Board board) {

        availablePositions = new ArrayList<>();

        Position currPosition = getPosition();

        if (currPosition.getRow() + 2 <= 8) {

            if (currPosition.getColumn() - 1 >= 'a') {
                Position position = board.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() + 2);
                position.getPiece()
                .ifPresentOrElse(curr -> {
                    if (curr.getColor() != getColor()) {
                        availablePositions.add(position);
                    }
                }, () -> availablePositions.add(position));
            }

            if (currPosition.getColumn() + 1 <= 'h') {
                Position position = board.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() + 2);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                availablePositions.add(position);
                            }
                        }, () -> availablePositions.add(position));
            }

        }

        if (currPosition.getRow() - 2 >= 1) {

            if (currPosition.getColumn() - 1 >= 'a') {
                Position position = board.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() - 2);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                availablePositions.add(position);
                            }
                        }, () -> availablePositions.add(position));
            }

            if (currPosition.getColumn() + 1 <= 'h') {
                Position position = board.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() - 2);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                availablePositions.add(position);
                            }
                        }, () -> availablePositions.add(position));
            }

        }

        if (currPosition.getColumn() + 2 <= 'h') {

            if (currPosition.getRow() + 1 <= 8) {
                Position position = board.getPositionAt((char)(currPosition.getColumn() + 2), currPosition.getRow() + 1);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                availablePositions.add(position);
                            }
                        }, () -> availablePositions.add(position));
            }

            if (currPosition.getRow() - 1 >= 1) {
                Position position = board.getPositionAt((char)(currPosition.getColumn() + 2), currPosition.getRow() - 1);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                availablePositions.add(position);
                            }
                        }, () -> availablePositions.add(position));
            }

        }

        if (currPosition.getColumn() - 2 >= 'a') {

            if (currPosition.getRow() + 1 <= 8) {
                Position position = board.getPositionAt((char)(currPosition.getColumn() - 2), currPosition.getRow() + 1);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                availablePositions.add(position);
                            }
                        }, () -> availablePositions.add(position));
            }

            if (currPosition.getRow() - 1 >= 1) {
                Position position = board.getPositionAt((char)(currPosition.getColumn() - 2), currPosition.getRow() - 1);
                position.getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                availablePositions.add(position);
                            }
                        }, () -> availablePositions.add(position));
            }

        }

        return availablePositions;
    }
}
