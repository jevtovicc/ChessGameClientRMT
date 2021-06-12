package gui;

import color.PieceColor;
import piece.*;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    JPanel pane;

    public GameWindow() {
        setTitle("Chess game");
        setSize(900, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pane = new JPanel();
        pane.setLayout(new GridLayout(8, 8));

        populateBoard();

        add(pane);
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
                                p = new Rook(pieceColor);
                                imageIcon = new ImageIcon("resources/rook-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor);
                                imageIcon = new ImageIcon("resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            break;
                        case 'b', 'g':
                            if (row == 1 || row == 8) {
                                p = new Knight(pieceColor);
                                imageIcon = new ImageIcon("resources/knight-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor);
                                imageIcon = new ImageIcon("resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            break;
                        case 'c', 'f':
                            if (row == 1 || row == 8) {
                                p = new Bishop(pieceColor);
                                imageIcon = new ImageIcon("resources/bishop-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor);
                                imageIcon = new ImageIcon("resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            break;
                        case 'd':
                            if (row == 1 || row == 8) {
                                p = new Queen(pieceColor);
                                imageIcon = new ImageIcon("resources/queen-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor);
                                imageIcon = new ImageIcon("resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            break;
                        case 'e':
                            if (row == 1 || row == 8) {
                                p = new King(pieceColor);
                                imageIcon = new ImageIcon("resources/king-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            else {
                                p = new Pawn(pieceColor);
                                imageIcon = new ImageIcon("resources/pawn-" + (pieceColor == PieceColor.Black ? "black" : "white") + ".png");
                            }
                            break;
                        default:
                            p = null;
                    };

                }


                java.awt.Color fieldColor = (col - 'a' + row - 1) % 2 == 0 ? new java.awt.Color(0xcc9b6d) : new java.awt.Color(0x393e46);
                Position position = new Position(p, col, row);
                if (imageIcon != null) {
                    Image image = imageIcon.getImage(); // transform it
                    Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    imageIcon = new ImageIcon(newimg);  // transform it back
                    position.setIcon(imageIcon);
                }
                position.setBackground(fieldColor);

                pane.add(position);
            }
        }
    }

}
