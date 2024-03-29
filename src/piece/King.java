package piece;

import gui.Board;
import gui.Position;

import java.util.*;
import java.util.stream.Collectors;

public class King extends Piece {

    public King(PieceColor color) {
        super(color, "resources/king-" + (color == PieceColor.Black ? "black" : "white") + ".png");
    }

    @Override
    public List<Position> calculateAvailablePositions(Board board) {

        availablePositions = new ArrayList<>();

        Position currPosition = getPosition();

        if (currPosition.getRow() + 1 <= 8) {
            Position temp = board.getPositionAt(currPosition.getColumn(), currPosition.getRow() + 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            availablePositions.add(temp);
                        }
                    }, () -> availablePositions.add(temp));
        }

        if (currPosition.getRow() - 1 >= 1) {
            Position temp = board.getPositionAt(currPosition.getColumn(), currPosition.getRow() - 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            availablePositions.add(temp);
                        }
                    }, () -> availablePositions.add(temp));
        }

        if (currPosition.getColumn() + 1 <= 'h') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow());
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            availablePositions.add(temp);
                        }
                    }, () -> availablePositions.add(temp));
        }

        if (currPosition.getColumn() - 1 >= 'a') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow());
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            availablePositions.add(temp);
                        }
                    }, () -> availablePositions.add(temp));
        }

        if (currPosition.getRow() + 1 <= 8 && currPosition.getColumn() + 1 <= 'h') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() + 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            availablePositions.add(temp);
                        }
                    }, () -> availablePositions.add(temp));
        }

        if (currPosition.getRow() + 1 <= 8 && currPosition.getColumn() - 1 >= 'a') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() + 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            availablePositions.add(temp);
                        }
                    }, () -> availablePositions.add(temp));
        }

        if (currPosition.getRow() - 1 >= 1 && currPosition.getColumn() + 1 <= 'h') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() - 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            availablePositions.add(temp);
                        }
                    }, () -> availablePositions.add(temp));
        }

        if (currPosition.getRow() - 1 >= 1 && currPosition.getColumn() - 1 >= 'a') {
            Position temp = board.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() - 1);
            temp.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() != getColor()) {
                            availablePositions.add(temp);
                        }
                    }, () -> availablePositions.add(temp));
        }

        return availablePositions;
    }


    /* returns true if check (if there is piece of opposite color that contains this king in it's available positions list) */
    public boolean isInDanger(Board board) {
        /* pieces of opposite color (opponent's pieces) */
        List<Piece> pieces =
                board.getPositions().stream()
                        .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() != getColor())
                        .map(position -> position.getPiece().get())
                        .collect(Collectors.toList());

        for (var piece : pieces) {
            var availablePositions = piece.calculateAvailablePositions(board);
            for (var avPos : availablePositions) {
                if (avPos.getPiece().orElse(null) == this) {
                    return true;
                }
            }
        }

        return false;
    }

    /* returns true if player in danger has at least one move that gets him out of danger (out of check situation) */
    public boolean isCheckMate(Board board) {

        List<Piece> pieces = board.getPositions().stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == getColor())
                .map(position -> position.getPiece().get())
                .collect(Collectors.toList());

        boolean preventingMoveFound = false;

        for (Piece piece : pieces) {

            /* if piece is pawn, keep track of moved property */
            boolean moved = false;
            if (piece instanceof Pawn) {
                moved = ((Pawn) piece).hasMoved();
            }

            Position previousPosition = piece.getPosition();

            List<Position> availablePositions = piece.calculateAvailablePositions(board);

            for (Position avPos : availablePositions) {

                Piece previousPiece = avPos.getPiece().orElse(null);

                piece.move(avPos);

                if (!isInDanger(board)) {
                    preventingMoveFound = true;
                }

                /* reset move made so it doesn't affect the original board */
                piece.move(previousPosition);
                if (previousPiece != null) {
                    previousPiece.move(avPos);
                }
            }

            /* if pawn, reset it's moved property to previous state */
            if (piece instanceof Pawn) {
                ((Pawn) piece).setMoved(moved);
            }

            if (preventingMoveFound) break;

        }

        return !preventingMoveFound;
    }

}
