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

    /* optional because piece could be null */
    public Optional<Piece> getPiece() { return Optional.ofNullable(piece); }
    public char getColumn() { return column; }
    public int getRow() { return row; }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* if position that is clicked has piece on it */
        if (piece != null) {
            /* if no piece is selected previously */
            if (GameWindow.getBoardPane().getSelectedPiece() == null) {
                if ((Client.isWhite() && piece.getColor() == Piece.PieceColor.Black) || (!Client.isWhite() && piece.getColor() == Piece.PieceColor.White)) {
                    return;
                }
                GameWindow.getBoardPane().setSelectedPiece(piece);
                GameWindow.getBoardPane().getSelectedPiece().calculateAvailablePositions(GameWindow.getBoardPane());

                /* for each available position that doesn't lead to check, enable action listener and set border to green */
                GameWindow.getBoardPane()
                        .getSelectedPiece()
                        .getAvailablePositions()
                        .stream()
                        .filter(avPos -> GameWindow.getBoardPane().getSelectedPiece().tryMove(GameWindow.getBoardPane(), avPos))
                        .forEach(avPos -> {
                            avPos.setBorder(new LineBorder(Color.GREEN, 4));
                            if (avPos.getActionListeners().length == 0) {
                                avPos.addActionListener(avPos);
                            }
                        });
            }
            /* if there is already selected piece */
            else {
                /* if piece that is clicked is opposite color and selected piece can move on that position */
                if (GameWindow.getBoardPane().getSelectedPiece().getColor() != piece.getColor() && GameWindow.getBoardPane().getSelectedPiece().getAvailablePositions().contains(this)
                        && GameWindow.getBoardPane().getSelectedPiece().tryMove(GameWindow.getBoardPane(), this)) {
                    Position source = GameWindow.getBoardPane().getSelectedPiece().getPosition();
                    GameWindow.getBoardPane().getSelectedPiece().move(this);
                    GameWindow.getHistoryPane().pushMoveToHistory(GameWindow.getBoardPane().getSelectedPiece(), source, this);
                    Client.makeMove(source, this);
                    GameWindow.getBoardPane().getSelectedPiece().resetAvailablePositions();
                    GameWindow.getBoardPane().setSelectedPiece(null);
                }
                /* if piece that is clicked is same color, update selected piece to clicked piece */
                else if (GameWindow.getBoardPane().getSelectedPiece().getColor() == piece.getColor()) {
                    GameWindow.getBoardPane().getSelectedPiece().resetAvailablePositions();
                    GameWindow.getBoardPane().setSelectedPiece(piece);
                    GameWindow.getBoardPane().getSelectedPiece().calculateAvailablePositions(GameWindow.getBoardPane());
                    /* for each available position that doesn't lead to check, enable action listener and set border to green */
                    GameWindow.getBoardPane()
                            .getSelectedPiece()
                            .getAvailablePositions()
                            .stream()
                            .filter(avPos -> GameWindow.getBoardPane().getSelectedPiece().tryMove(GameWindow.getBoardPane(), avPos))
                            .forEach(avPos -> {
                                avPos.setBorder(new LineBorder(Color.GREEN, 4));
                                if (avPos.getActionListeners().length == 0) {
                                    avPos.addActionListener(avPos);
                                }
                            });
                }

            }
        }
        /* if position that is clicked doesn't have piece on it and selected piece can move on that position */
        else {
            if (GameWindow.getBoardPane().getSelectedPiece() != null) {
                if (GameWindow.getBoardPane().getSelectedPiece().getAvailablePositions().contains(this) && GameWindow.getBoardPane().getSelectedPiece().tryMove(GameWindow.getBoardPane(),this)) {
                    Position source = GameWindow.getBoardPane().getSelectedPiece().getPosition();
                    GameWindow.getBoardPane().getSelectedPiece().move(this);
                    GameWindow.getHistoryPane().pushMoveToHistory(GameWindow.getBoardPane().getSelectedPiece(), source, this);
                    Client.makeMove(source, this);
                }
                GameWindow.getBoardPane().getSelectedPiece().resetAvailablePositions();
                GameWindow.getBoardPane().setSelectedPiece(null);
            }
        }

    }

    @Override
    public String toString() { return "(" + row + ", " + column + "," + piece + ")";}
}
