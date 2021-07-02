package gui.boardpopulator;

import gui.Board;
import gui.Position;
import piece.*;
import startup.Client;

import javax.swing.*;
import java.awt.*;

public class BlackBoardPopulator implements BoardPopulator {

    @Override
    public void populateBoard(Board board) {

        /* filler label for upper left corner */
        JLabel emptyLabel = new JLabel("");
        board.add(emptyLabel);

        /* upper column labels */
        for (char col = 'a'; col <= 'h'; col++) {
            JLabel colLabel = new JLabel(String.valueOf(col));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            board.add(colLabel);
        }

        /* filler label for upper right corner */
        JLabel emptyLabel2 = new JLabel("");
        board.add(emptyLabel2);

        for (int row = 1; row <= 8; row++) {

            /* row labels on left side */
            JLabel leftRowLabel = new JLabel(String.valueOf(row));
            leftRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            board.add(leftRowLabel);

            for (char col = 'a'; col <= 'h'; col++) {

                Piece piece = null;

                /* rows: 1, 2, 7 and 8 will be populated with pieces, others will be empty */
                if (row == 1 || row == 2 || row == 7 || row == 8) {

                    Piece.PieceColor pieceColor = row == 1 || row == 2 ?
                            Piece.PieceColor.White :
                            Piece.PieceColor.Black;

                    if (row == 2 || row == 7) {
                        piece = (pieceColor == Piece.PieceColor.Black) ?
                                new BlackPawn() :
                                new WhitePawn();
                    } else {
                        piece = switch (col) {
                            case 'a', 'h' -> new Rook(pieceColor);
                            case 'b', 'g' -> new Knight(pieceColor);
                            case 'c', 'f' ->  new Bishop(pieceColor);
                            case 'd' -> new Queen(pieceColor);
                            case 'e' -> new King(pieceColor);
                            default -> throw new IllegalStateException("Invalid column");
                        };
                    }
                }

                /* calculate field color with this formula */
                Color fieldColor = (col - 'a' + row - 1) % 2 == 1 ?
                        new Color(0x493323) :
                        new Color(0xE1BC91);

                Position position = new Position(piece, col, row);
                position.setBackground(fieldColor);

                if (piece != null) {
                    position.setIcon(new ImageIcon(piece.getPngFilePath()));
                    piece.setPosition(position);
                }

                board.getPositions().add(position); // add to positions list
                board.add(position); // add to JPanel
            }

            /* row labels on right side */
            JLabel rightRowLabel = new JLabel(String.valueOf(row));
            rightRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            board.add(rightRowLabel);
        }

        /* filler label for lower left corner */
        JLabel emptyLabel3 = new JLabel("");
        board.add(emptyLabel3);

        /* lower column labels */
        for (char col = 'a'; col <= 'h'; col++) {
            JLabel colLabel = new JLabel(String.valueOf(col));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            board.add(colLabel);
        }

        /* filler label for lower right corner */
        JLabel emptyLabel4 = new JLabel("");
        board.add(emptyLabel4);
    }
}
