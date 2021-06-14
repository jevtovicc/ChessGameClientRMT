package gui;

import piece.Piece;

import javax.swing.*;
import java.util.Optional;

public class Position extends JButton {
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

}
