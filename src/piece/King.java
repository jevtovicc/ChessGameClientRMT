package piece;

import gui.GameWindow;
import gui.Position;

import java.util.*;
import java.util.stream.Collectors;

public class King extends Piece {

    public King(Piece.PieceColor c, String pngFilePath) {
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


    public boolean isInDanger() {
        List<Piece> pieces =
                GameWindow.positions.stream()
                        .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() != getColor())
                        .map(position -> position.getPiece().get())
                        .collect(Collectors.toList());

        for (var piece : pieces) {
            if (piece instanceof King)
                continue;
            var availablePositions = piece.getAvailablePositions();
            for (var avPos : availablePositions) {
                if (avPos.getPiece().isPresent() && avPos.getPiece().get().equals(this)) {
                    return true;
                }
            }
        }

        return false;
    }


    public boolean isCheckMate() { return getPreventingPositions().isEmpty(); }

    public Map<Piece, List<Position>> getPreventingPositions() {

        List<Piece> pieces = GameWindow.positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == getColor())
                .map(position -> position.getPiece().get())
                .collect(Collectors.toList());

        Map<Piece, List<Position>> map = new HashMap<>();

        for (Piece piece : pieces) {

            /* if piece is pawn, keep track of moved property */
            boolean moved = false;
            if (piece instanceof Pawn) {
                moved = ((Pawn) piece).hasMoved();
            }

            Position previousPosition = piece.getPosition();

            List<Position> availablePositions = piece.getAvailablePositions();
            List<Position> preventingPositions = new ArrayList<>();

            for (Position avPos : availablePositions) {

                Piece previousPiece = null;

                if (avPos.getPiece().isPresent()) {
                    previousPiece = avPos.getPiece().get();
                }

                piece.move(avPos);

                if (!isInDanger()) {
                    piece.move(previousPosition);
                    if (previousPiece != null) {
                        previousPiece.move(avPos);
                    }
                    preventingPositions.add(avPos);
                }

                piece.move(previousPosition);

                if (previousPiece != null) {
                    previousPiece.move(avPos);
                }
            }

            if (piece instanceof Pawn) {
                ((Pawn) piece).setMoved(moved);
            }

            if (!preventingPositions.isEmpty()) {
                map.put(piece, preventingPositions);
            }
        }

        return map;
    }
}
