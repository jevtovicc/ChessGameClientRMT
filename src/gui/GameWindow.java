package gui;

import startup.Client;
import piece.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameWindow extends JFrame {

    JPanel pane;
    public static java.util.List<Position> positions;
    static Piece selectedPiece;
    static java.util.List<Position> availablePositions;

    public GameWindow() {
        setTitle("Chess game");
        setSize(900, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pane = new JPanel();
        pane.setLayout(new GridLayout(8, 8));

        positions = new ArrayList<>();

        populateBoard();

        add(pane);
    }

    private void populateBoard() {

        for (int row = 1; row <= 8; row++) {
            for (char col = 'a'; col <= 'h'; col++) {

                Piece p = null;
                ImageIcon imageIcon = null;

                if (row == 1 || row == 2 || row == 7 || row == 8) {

                    Piece.PieceColor pieceColor = row == 1 || row == 2 ? Piece.PieceColor.Black : Piece.PieceColor.White;

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

                Color fieldColor = (col - 'a' + row - 1) % 2 == 0 ? new Color(0x493323) : new Color(0xE1BC91);
                Position position = new Position(p, col, row);
                position.setIcon(imageIcon);
                position.setBackground(fieldColor);

                // setup initial state
                if (Client.isWhite() && (p == null || p.getColor() == Piece.PieceColor.White || !Client.isWhite()))
                    position.addActionListener(position);

                if (p != null) p.setPosition(position);

                positions.add(position);
                pane.add(position);
            }
        }
    }

    public static Position getPositionAt(char col, int row) {
        return positions.stream()
                .filter(p -> p.getRow() == row && p.getColumn() == col)
                .findFirst()
                .get();
    }

    public static void setPieceAt(Piece piece, char col, int row) {
        Position position = getPositionAt(col, row);
        position.setPiece(piece);
        position.setIcon(new ImageIcon(piece.getPngFilePath()));
        piece.setPosition(position);
    }

    public static void resetAvailablePositions() {
        availablePositions.forEach(p -> p.setBorder(null));
        availablePositions.clear();
    }

    public static void toggleActionListener(boolean enabled) {
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

    public static boolean calculateIfInDanger() {
        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == pieceColor)
                .map(p -> p.getPiece().get())
                .filter(p -> p instanceof King)
                .findFirst()
                .get();
        return king.isInDanger();
    }

    public static boolean calculateIfCheckmate() {
        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == pieceColor)
                .map(p -> p.getPiece().get())
                .filter(p -> p instanceof King)
                .findFirst()
                .get();
        return king.isCheckMate();
    }

    public static void func() {

        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == pieceColor)
                .map(p -> p.getPiece().get())
                .filter(p -> p instanceof King)
                .findFirst()
                .get();

        var map = king.getPreventingPositions();

        List<Piece> pieces = GameWindow.positions.stream()
                .filter(p -> p.getPiece().isPresent() && p.getPiece().get().getColor() == king.getColor())
                .map(position -> position.getPiece().get())
                .collect(Collectors.toList());

        for (Piece piece : pieces) {
            piece.getPosition().removeActionListener(piece.getPosition());
//            }
        }

        map.forEach((piece, preventingPositions) -> {
            piece.getPosition().addActionListener(e -> {
                if (selectedPiece == null) {
                    selectedPiece = piece;
                    preventingPositions.forEach(position -> {
                        position.removeActionListener(position);
                        position.setBorder(new LineBorder(Color.GREEN, 4));
                        position.addActionListener(event -> {
                            Position source = piece.getPosition();
                            Arrays.stream(piece.getPosition().getActionListeners())
                                    .forEach(al -> piece.getPosition().removeActionListener(al));
                            piece.move(position);
                            resetPreventingPositions(map);
                            Client.makeMove(source, position);
                            selectedPiece = null;
                        });
                    });
                } else {
                    selectedPiece = piece;
                    resetPreventingPositions(map);
                    preventingPositions.forEach(position -> {
                        position.removeActionListener(position);
                        position.setBorder(new LineBorder(Color.GREEN, 4));
                        position.addActionListener(event -> {
                            Position source = piece.getPosition();
                            Arrays.stream(piece.getPosition().getActionListeners())
                                    .forEach(al -> piece.getPosition().removeActionListener(al));
                            piece.move(position);
                            resetPreventingPositions(map);
                            Client.makeMove(source, position);
                            selectedPiece = null;
                        });
                    });
                }

            });
        });
    }

    public static void resetPreventingPositions(Map<Piece, List<Position>> map) {
        map.forEach((piece, preventingPositions) -> {
            Arrays.stream(piece.getPosition().getActionListeners())
                    .forEach(al -> piece.getPosition().removeActionListener(al));
            preventingPositions.forEach(position -> {
                position.setBorder(null);
                Arrays.stream(position.getActionListeners())
                        .forEach(al -> position.removeActionListener(al));
            });
        });
    }

    public static boolean tryMove(Piece piece, Position destination) {

        boolean moved = false;
        if (piece instanceof Pawn) {
            moved = ((Pawn) piece).hasMoved();
        }

        boolean valid = true;

        Position source = piece.getPosition();
        var optionalPiece = destination.getPiece();

        piece.move(destination);

        if (calculateIfInDanger()) {
            valid = false;
        }

        piece.move(source);
        optionalPiece.ifPresent(p -> p.move(destination));

        if (piece instanceof Pawn) {
            ((Pawn) piece).setMoved(moved);
        }

        return valid;
    }

}
