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
        for (char col = 'a'; col <= 'h'; col++) {
            JLabel colLabel = new JLabel(String.valueOf(col));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            board.add(colLabel);
        }
        /* filler label for upper right corner */
        JLabel emptyLabel2 = new JLabel("");
        board.add(emptyLabel2);

        for (int row = 1; row <= 8; row++) {

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
                                new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                new WhitePawn(pieceColor, "resources/pawn-white.png");
                    } else {
                        piece = switch (col) {
                            case 'a', 'h' -> new Rook(pieceColor, "resources/rook-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            case 'b', 'g' -> new Knight(pieceColor, "resources/knight-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            case 'c', 'f' ->  new Bishop(pieceColor, "resources/bishop-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            case 'd' -> new Queen(pieceColor, "resources/queen-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            case 'e' -> new King(pieceColor, "resources/king-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            default -> null;
                        };
                    }
                }

                /* calculate field color with this formula */
                Color fieldColor = (col - 'a' + row - 1) % 2 == 1 ? new Color(0x493323) : new Color(0xE1BC91);
                Position position = new Position(piece, col, row);
                position.setBackground(fieldColor);

                // setup initial state
                if (Client.isWhite() && (piece == null || piece.getColor() == Piece.PieceColor.White || !Client.isWhite()))
                    position.addActionListener(position);

                if (piece != null) {
                    position.setIcon(new ImageIcon(piece.getPngFilePath()));
                    piece.setPosition(position);
                }

                board.getPositions().add(position); // add to positions list
                board.add(position); // add to JPanel
            }

            JLabel rightRowLabel = new JLabel(String.valueOf(row));
            rightRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            board.add(rightRowLabel);
        }

        /* filler label for lower left corner */
        JLabel emptyLabel3 = new JLabel("");
        board.add(emptyLabel3);
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
