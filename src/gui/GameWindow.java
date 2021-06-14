package gui;

import board.Move;
import color.PieceColor;
import piece.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    JPanel pane;
    static java.util.List<Position> positions;
    Piece selectedPiece;
    static java.util.List<Move> availableMoves;

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
//        setVisible(true); // for testing purposes
    }

    private void populateBoard() {

        for (int row = 1; row <= 8; row++) {
            for (char col = 'a'; col <= 'h'; col++) {

                Piece p = null;
                ImageIcon imageIcon = null;

                if (row == 1 || row == 2 || row == 7 || row == 8) {

                    PieceColor pieceColor = row == 1 || row == 2 ? PieceColor.Black : PieceColor.White;

                    switch (col) {
                        case 'a', 'h':
                            if (row == 1 || row == 8) {
                                p = new Rook(pieceColor, "resources/rook-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor, "resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                            break;
                        case 'b', 'g':
                            if (row == 1 || row == 8) {
                                p = new Knight(pieceColor, "resources/knight-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor, "resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                            break;
                        case 'c', 'f':
                            if (row == 1 || row == 8) {
                                p = new Bishop(pieceColor, "resources/bishop-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor, "resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                            break;
                        case 'd':
                            if (row == 1 || row == 8) {
                                p = new Queen(pieceColor, "resources/queen-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor, "resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                            break;
                        case 'e':
                            if (row == 1 || row == 8) {
                                p = new King(pieceColor, "resources/king-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor, "resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            imageIcon = new ImageIcon(p.getPngFilePath());
                            break;
                        default:
                            p = null;
                    };

                }

                Color fieldColor = (col - 'a' + row - 1) % 2 == 0 ? new Color(0xcc9b6d) : new Color(0x393e46);
                Position position = new Position(p, col, row);
                position.setIcon(imageIcon);
                position.setBackground(fieldColor);

                position.addActionListener(e -> {
                    position.getPiece()
                            .ifPresentOrElse(piece -> {
                                selectedPiece = piece;
                                availableMoves = piece.getPossibleMoves();
                                availableMoves.forEach(m -> {
                                        Position availablePosition = getPositionAt(m.getColumn(), m.getRow());
                                        availablePosition.setBorder(new LineBorder(Color.GREEN, 4));
                                    });
                            }, () -> {
                                if (selectedPiece != null) {
                                    Move m = new Move(position.getRow(), position.getColumn());
                                    if (availableMoves.contains(m)) {
                                        selectedPiece.move(m);
                                    }
                                    resetAvailableMoves();
                                }
                            });
                });

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
        ImageIcon imageIcon = new ImageIcon(piece.getPngFilePath());
        position.setIcon(imageIcon);
        piece.setPosition(position);
    }

    private static void resetAvailableMoves() {
        availableMoves.forEach(m -> {
            Position p = getPositionAt(m.getColumn(), m.getRow());
            p.setBorder(null);
        });
        availableMoves.clear();
    }


    // for testing purposes
//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new GameWindow();
//    }

}
