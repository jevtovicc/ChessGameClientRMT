package gui;

import piece.*;
import startup.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    private final java.util.List<Position> positions;
    private Piece selectedPiece;

    public java.util.List<Position> getPositions() { return positions; }
    public Piece getSelectedPiece() { return selectedPiece; }
    public void setSelectedPiece(Piece piece) { selectedPiece = piece; }

    public Board() {
        setLayout(new GridLayout(8, 8));
        setPreferredSize(new Dimension(900, 600));
        setMaximumSize(new Dimension(900, 600));

        positions = new ArrayList<>();
        if (Client.isWhite())
            populateBoardWhite();
        else populateBoardBlack();
    }

    private void populateBoardWhite() {

        for (int row = 8; row >= 1; row--) {

            for (char col = 'a'; col <= 'h'; col++) {

                Piece p = null;
                ImageIcon imageIcon = null;

                if (row == 1 || row == 2 || row == 7 || row == 8) {

                    Piece.PieceColor pieceColor = row == 1 || row == 2 ? Piece.PieceColor.White : Piece.PieceColor.Black;

                    switch (col) {
                        case 'a', 'h' -> {
                            if (row == 1 || row == 8) {
                                p = new Rook(pieceColor, "resources/rook-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        case 'b', 'g' -> {
                            if (row == 1 || row == 8) {
                                p = new Knight(pieceColor, "resources/knight-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        case 'c', 'f' -> {
                            if (row == 1 || row == 8) {
                                p = new Bishop(pieceColor, "resources/bishop-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor== Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        case 'd' -> {
                            if (row == 1 || row == 8) {
                                p = new Queen(pieceColor, "resources/queen-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        case 'e' -> {
                            if (row == 1 || row == 8) {
                                p = new King(pieceColor, "resources/king-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        default -> p = null;
                    }

                }

                Color fieldColor = (col - 'a' + row - 1) % 2 == 1 ? new Color(0x493323) : new Color(0xE1BC91);
                Position position = new Position(p, col, row);
                position.setIcon(imageIcon);
                position.setBackground(fieldColor);

                // setup initial state
                if (Client.isWhite() && (p == null || p.getColor() == Piece.PieceColor.White || !Client.isWhite()))
                    position.addActionListener(position);

                if (p != null) p.setPosition(position);

                positions.add(position);
                add(position);
            }
        }
    }

    private void populateBoardBlack() {

        for (int row = 1; row <= 8; row++) {

            for (char col = 'a'; col <= 'h'; col++) {

                Piece p = null;
                ImageIcon imageIcon = null;

                if (row == 1 || row == 2 || row == 7 || row == 8) {

                    Piece.PieceColor pieceColor = row == 1 || row == 2 ? Piece.PieceColor.White : Piece.PieceColor.Black;

                    switch (col) {
                        case 'a', 'h' -> {
                            if (row == 1 || row == 8) {
                                p = new Rook(pieceColor, "resources/rook-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        case 'b', 'g' -> {
                            if (row == 1 || row == 8) {
                                p = new Knight(pieceColor, "resources/knight-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        case 'c', 'f' -> {
                            if (row == 1 || row == 8) {
                                p = new Bishop(pieceColor, "resources/bishop-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor== Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        case 'd' -> {
                            if (row == 1 || row == 8) {
                                p = new Queen(pieceColor, "resources/queen-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        case 'e' -> {
                            if (row == 1 || row == 8) {
                                p = new King(pieceColor, "resources/king-" + (pieceColor == Piece.PieceColor.Black ? "black" : "white") + ".png");
                            } else {
                                p = pieceColor == Piece.PieceColor.Black ?
                                        new BlackPawn(pieceColor, "resources/pawn-black.png") :
                                        new WhitePawn(pieceColor, "resources/pawn-white.png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                        }
                        default -> p = null;
                    }

                }

                Color fieldColor = (col - 'a' + row - 1) % 2 == 1 ? new Color(0x493323) : new Color(0xE1BC91);
                Position position = new Position(p, col, row);
                position.setIcon(imageIcon);
                position.setBackground(fieldColor);

                // setup initial state
                if (Client.isWhite() && (p == null || p.getColor() == Piece.PieceColor.White || !Client.isWhite()))
                    position.addActionListener(position);

                if (p != null) p.setPosition(position);

                positions.add(position);
                add(position);
            }
        }
    }


    public Position getPositionAt(char col, int row) {
        return positions.stream()
                .filter(p -> p.getRow() == row && p.getColumn() == col)
                .findFirst()
                .get();
    }

    public void setPieceAt(Piece piece, char col, int row) {
        Position position = getPositionAt(col, row);
        position.setPiece(piece);
        position.setIcon(new ImageIcon(piece.getPngFilePath()));
        piece.setPosition(position);
    }

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

    public boolean calculateIfInDanger() {
        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == pieceColor)
                .map(p -> p.getPiece().get())
                .filter(p -> p instanceof King)
                .findFirst()
                .get();
        return king.isInDanger();
    }

    public boolean calculateIfCheckmate() {
        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == pieceColor)
                .map(p -> p.getPiece().get())
                .filter(p -> p instanceof King)
                .findFirst()
                .get();
        return king.isCheckMate();
    }

}
