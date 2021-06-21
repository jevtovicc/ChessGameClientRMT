package gui;

import startup.Client;
import piece.Piece;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class Position extends JButton implements ActionListener {
    private Piece piece;
    private final char column; // a-h
    private final int row; // 1-8

    public Position(Piece p, char col, int r) {
        piece = p;
        column = col;
        row = r;
    }

    public void setPiece(Piece p) { piece = p; }

    public Optional<Piece> getPiece() { return Optional.ofNullable(piece); }
    public char getColumn() { return column; }
    public int getRow() { return row; }

    public boolean isAvailable() { return piece == null; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (piece != null) {
            if (GameWindow.selectedPiece == null) {
                if ((Client.isWhite() && piece.getColor() == Piece.PieceColor.Black) || (!Client.isWhite() && piece.getColor() == Piece.PieceColor.White)) {
                    return;
                }
                GameWindow.selectedPiece = piece;
                GameWindow.availablePositions = piece.getAvailablePositions();
                GameWindow.availablePositions.forEach(avPos -> {
                    if (GameWindow.selectedPiece.tryMove(avPos)) {
                        avPos.setBorder(new LineBorder(Color.GREEN, 4));
                        if (avPos.getActionListeners().length == 0) {
                            avPos.addActionListener(avPos);
                        }
                    }
                });
            } else {
                if (GameWindow.selectedPiece.getColor() != piece.getColor() && GameWindow.availablePositions.contains(this)) {
                    Position source = GameWindow.selectedPiece.getPosition();
                    GameWindow.selectedPiece.move(this);
                    Client.makeMove(source, this);
                    GameWindow.selectedPiece = null;
                    GameWindow.resetAvailablePositions();
                } else if (GameWindow.selectedPiece.getColor() == piece.getColor()) {
                    GameWindow.resetAvailablePositions();
                    GameWindow.selectedPiece = piece;
                    GameWindow.availablePositions = piece.getAvailablePositions();
                    GameWindow.availablePositions.forEach(avPos -> {
                        if (GameWindow.selectedPiece.tryMove(avPos)) {
                            avPos.setBorder(new LineBorder(Color.GREEN, 4));
                            if (avPos.getActionListeners().length == 0) {
                                avPos.addActionListener(avPos);
                            }
                        }

                    });
                }

            }
        } else {
            if (GameWindow.selectedPiece != null) {
                if (GameWindow.availablePositions.contains(this)) {
                    Position source = GameWindow.selectedPiece.getPosition();
                    GameWindow.selectedPiece.move(this);
                    Client.makeMove(source, this);
                }
                GameWindow.resetAvailablePositions();
                GameWindow.selectedPiece = null;
            }
        }

    }

    @Override
    public String toString() { return "(" + row + ", " + column + "," + piece + ")";}
}
