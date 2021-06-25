package gui;

import piece.*;
import startup.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    private final java.util.List<Position> positions; /* positions on the board */
    private Piece selectedPiece; /* currently selected piece that player wants to move */

    public java.util.List<Position> getPositions() { return positions; }
    public Piece getSelectedPiece() { return selectedPiece; }
    public void setSelectedPiece(Piece piece) { selectedPiece = piece; }

    public Board() {
        setLayout(new GridLayout(10, 10));
        setPreferredSize(new Dimension(1000, 600));
        setMaximumSize(new Dimension(1000, 600));

        positions = new ArrayList<>();
        /* populate board differently for different perspectives */
        if (Client.isWhite())
            populateBoardWhite();
        else populateBoardBlack();
    }

    private void populateBoardWhite() {

        /* filler label for upper left corner */
        JLabel emptyLabel = new JLabel("");
        add(emptyLabel);
        for (char col = 'a'; col <= 'h'; col++) {
            JLabel colLabel = new JLabel(String.valueOf(col));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(colLabel);
        }
        /* filler label for upper right corner */
        JLabel emptyLabel2 = new JLabel("");
        add(emptyLabel2);

        for (int row = 8; row >= 1; row--) {

            JLabel leftRowLabel = new JLabel(String.valueOf(row));
            leftRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(leftRowLabel);

            for (char col = 'a'; col <= 'h'; col++) {

                Piece piece = null;
                ImageIcon imageIcon = null;

                /* rows: 1, 2, 7 and 8 will be populated with pieces, others will be empty */
                if (row == 1 || row == 2 || row == 7 || row == 8) {

                    Piece.PieceColor pieceColor = row == 1 || row == 2 ? Piece.PieceColor.White : Piece.PieceColor.Black;

                    switch (col) {
                        case 'a', 'h' -> {
                            if (row == 1 || row == 8) {
                                piece = new Rook(pieceColor, "resources/rook-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        case 'b', 'g' -> {
                            if (row == 1 || row == 8) {
                                piece = new Knight(pieceColor, "resources/knight-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        case 'c', 'f' -> {
                            if (row == 1 || row == 8) {
                                piece = new Bishop(pieceColor, "resources/bishop-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor== Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        case 'd' -> {
                            if (row == 1 || row == 8) {
                                piece = new Queen(pieceColor, "resources/queen-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        case 'e' -> {
                            if (row == 1 || row == 8) {
                                piece = new King(pieceColor, "resources/king-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        default -> piece = null;
                    }

                }

                /* calculate field color with this formula */
                Color fieldColor = (col - 'a' + row - 1) % 2 == 1 ? new Color(0x493323) : new Color(0xE1BC91);
                Position position = new Position(piece, col, row);
                position.setIcon(imageIcon);
                position.setBackground(fieldColor);

                // setup initial state
                if (Client.isWhite() && (piece == null || piece.getColor() == Piece.PieceColor.White || !Client.isWhite()))
                    position.addActionListener(position);


                if (piece != null)
                    piece.setPosition(position);

                positions.add(position); // add to positions list
                add(position); // add to JPanel
            }

            JLabel rightRowLabel = new JLabel(String.valueOf(row));
            rightRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(rightRowLabel);
        }

        /* filler label for lower left corner */
        JLabel emptyLabel3 = new JLabel("");
        add(emptyLabel3);
        for (char col = 'a'; col <= 'h'; col++) {
            JLabel colLabel = new JLabel(String.valueOf(col));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(colLabel);
        }
        /* filler label for lower right corner */
        JLabel emptyLabel4 = new JLabel("");
        add(emptyLabel4);
    }

    private void populateBoardBlack() {

        /* filler label for upper left corner */
        JLabel emptyLabel = new JLabel("");
        add(emptyLabel);
        for (char col = 'a'; col <= 'h'; col++) {
            JLabel colLabel = new JLabel(String.valueOf(col));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(colLabel);
        }
        /* filler label for upper right corner */
        JLabel emptyLabel2 = new JLabel("");
        add(emptyLabel2);

        for (int row = 1; row <= 8; row++) {

            JLabel leftRowLabel = new JLabel(String.valueOf(row));
            leftRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(leftRowLabel);

            for (char col = 'a'; col <= 'h'; col++) {

                Piece piece = null;
                ImageIcon imageIcon = null;

                /* rows: 1, 2, 7 and 8 will be populated with pieces, others will be empty */
                if (row == 1 || row == 2 || row == 7 || row == 8) {

                    Piece.PieceColor pieceColor = row == 1 || row == 2 ? Piece.PieceColor.White : Piece.PieceColor.Black;

                    switch (col) {
                        case 'a', 'h' -> {
                            if (row == 1 || row == 8) {
                                piece = new Rook(pieceColor, "resources/rook-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        case 'b', 'g' -> {
                            if (row == 1 || row == 8) {
                                piece = new Knight(pieceColor, "resources/knight-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        case 'c', 'f' -> {
                            if (row == 1 || row == 8) {
                                piece = new Bishop(pieceColor, "resources/bishop-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor== Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        case 'd' -> {
                            if (row == 1 || row == 8) {
                                piece = new Queen(pieceColor, "resources/queen-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        case 'e' -> {
                            if (row == 1 || row == 8) {
                                piece = new King(pieceColor, "resources/king-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                piece = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(piece.getPngFilePath());
                        }
                        default -> piece = null;
                    }

                }

                /* calculate field color with this formula */
                Color fieldColor = (col - 'a' + row - 1) % 2 == 1 ? new Color(0x493323) : new Color(0xE1BC91);
                Position position = new Position(piece, col, row);
                position.setIcon(imageIcon);
                position.setBackground(fieldColor);

                // setup initial state
                if (Client.isWhite() && (piece == null || piece.getColor() == Piece.PieceColor.White || !Client.isWhite()))
                    position.addActionListener(position);

                if (piece != null)
                    piece.setPosition(position);

                positions.add(position); // add to positions list
                add(position); // add to JPanel
            }

            JLabel rightRowLabel = new JLabel(String.valueOf(row));
            rightRowLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(rightRowLabel);
        }

        /* filler label for lower left corner */
        JLabel emptyLabel3 = new JLabel("");
        add(emptyLabel3);
        for (char col = 'a'; col <= 'h'; col++) {
            JLabel colLabel = new JLabel(String.valueOf(col));
            colLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(colLabel);
        }
        /* filler label for lower right corner */
        JLabel emptyLabel4 = new JLabel("");
        add(emptyLabel4);
    }


    /* get position object based on column and row */
    public Position getPositionAt(char col, int row) {
        return positions.stream()
                .filter(p -> p.getRow() == row && p.getColumn() == col)
                .findFirst()
                .get(); // get without is present is okay because column and row will be in range (a-h, 1-8)
    }

    /* set piece at position that can be found based on column and row */
    public void setPieceAt(Piece piece, char col, int row) {
        Position position = getPositionAt(col, row);
        position.setPiece(piece);
        position.setIcon(new ImageIcon(piece.getPngFilePath()));
        piece.setPosition(position);
    }

    /* when player makes a move, disable action listeners on all positions while other player doesn't make his move */
    public void toggleActionListeners(boolean enabled) {
        for (Position position : positions) {
            position.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() == (Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black)) {
                            if (enabled) {
                                position.addActionListener(position);
                            } else {
                                position.removeActionListener(position);
                            }
                        }
                    }, () -> {
                        if (enabled) {
                            position.addActionListener(position);
                        } else {
                            position.removeActionListener(position);
                        }
                    });
        }
    }

    /* find king that corresponds to player's color and call it's isInDanger method */
    public boolean calculateIfInDanger() {
        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == pieceColor)
                .map(p -> p.getPiece().get())
                .filter(p -> p instanceof King)
                .findFirst()
                .get();
        return king.isInDanger(this);
    }

    /* find king that corresponds to player's color and call it's isCheckMate method */
    public boolean calculateIfCheckmate() {
        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == pieceColor)
                .map(p -> p.getPiece().get())
                .filter(p -> p instanceof King)
                .findFirst()
                .get(); // get without is present is okay because there will always be king on the board */
        return king.isCheckMate(this);
    }

}
