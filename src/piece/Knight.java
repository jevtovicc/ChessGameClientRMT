package piece;

import board.Move;
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
    public void move(Move m) {
        Position position = getPosition();
        position.setPiece(null); // set previous position to null
        position.setIcon(null);
        GameWindow.setPieceAt(this, m.getColumn(), m.getRow());
    }

    @Override
    public List<Move> getPossibleMoves() {

        List<Move> moves = new ArrayList<>();

        Position currPosition = getPosition();

        if (currPosition.getRow() + 2 <= 8) {

            if (currPosition.getColumn() - 1 >= 'a') {
                GameWindow.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() + 2)
                        .getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                System.out.println("adding..");
                                moves.add(new Move(currPosition.getRow() + 2, (char)(currPosition.getColumn() - 1)));
                            }
                        }, () -> moves.add(new Move(currPosition.getRow() + 2, (char)(currPosition.getColumn() - 1))));
            }

            if (currPosition.getColumn() + 1 <= 'h') {
                GameWindow.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() + 2)
                        .getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                moves.add(new Move(currPosition.getRow() + 2, (char)(currPosition.getColumn() + 1)));
                            }
                        }, () -> moves.add(new Move(currPosition.getRow() + 2, (char)(currPosition.getColumn() + 1))));
            }

        }

        if (currPosition.getRow() - 2 >= 1) {

            if (currPosition.getColumn() - 1 >= 'a') {
                GameWindow.getPositionAt((char)(currPosition.getColumn() - 1), currPosition.getRow() - 2)
                        .getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                moves.add(new Move(currPosition.getRow() - 2, (char)(currPosition.getColumn() - 1)));
                            }
                        }, () -> moves.add(new Move(currPosition.getRow() - 2, (char)(currPosition.getColumn() - 1))));
            }

            if (currPosition.getColumn() + 1 <= 'h') {
                GameWindow.getPositionAt((char)(currPosition.getColumn() + 1), currPosition.getRow() - 2)
                        .getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                moves.add(new Move(currPosition.getRow() - 2, (char)(currPosition.getColumn() + 1)));
                            }
                        }, () -> moves.add(new Move(currPosition.getRow() - 2, (char)(currPosition.getColumn() + 1))));
            }

        }

        if (currPosition.getColumn() + 2 <= 'h') {

            if (currPosition.getRow() + 1 <= 8) {
                GameWindow.getPositionAt((char)(currPosition.getColumn() + 2), currPosition.getRow() + 1)
                        .getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                moves.add(new Move(currPosition.getRow() + 1, (char)(currPosition.getColumn() + 2)));
                            }
                        }, () -> moves.add(new Move(currPosition.getRow() + 1, (char)(currPosition.getColumn() + 2))));
            }

            if (currPosition.getRow() - 1 >= 1) {
                GameWindow.getPositionAt((char)(currPosition.getColumn() + 2), currPosition.getRow() - 1)
                        .getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                moves.add(new Move(currPosition.getRow() - 1, (char)(currPosition.getColumn() + 2)));
                            }
                        }, () -> moves.add(new Move(currPosition.getRow() - 1, (char)(currPosition.getColumn() + 2))));
            }

        }

        if (currPosition.getColumn() - 2 >= 'a') {

            if (currPosition.getRow() + 1 <= 8) {
                GameWindow.getPositionAt((char)(currPosition.getColumn() - 2), currPosition.getRow() + 1)
                        .getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                moves.add(new Move(currPosition.getRow() + 1, (char)(currPosition.getColumn() - 2)));
                            }
                        }, () -> moves.add(new Move(currPosition.getRow() + 1, (char)(currPosition.getColumn() - 2))));
            }

            if (currPosition.getRow() - 1 >= 1) {
                GameWindow.getPositionAt((char)(currPosition.getColumn() - 2), currPosition.getRow() - 1)
                        .getPiece()
                        .ifPresentOrElse(curr -> {
                            if (curr.getColor() != getColor()) {
                                moves.add(new Move(currPosition.getRow() - 1, (char)(currPosition.getColumn() - 2)));
                            }
                        }, () -> moves.add(new Move(currPosition.getRow() - 1, (char)(currPosition.getColumn() - 2))));
            }

        }

        return moves;
    }
}
