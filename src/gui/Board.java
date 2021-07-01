package gui;

import gui.boardpopulator.BlackBoardPopulator;
import gui.boardpopulator.WhiteBoardPopulator;
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
        if (Client.isWhite()) {
            new WhiteBoardPopulator().populateBoard(this);
        } else {
            new BlackBoardPopulator().populateBoard(this);
        }
    }


    /* get position object based on column and row */
    public Position getPositionAt(char col, int row) {
        return positions.stream()
                .filter(p -> p.getRow() == row && p.getColumn() == col)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
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
                .orElseThrow(() -> new IllegalStateException("King is not found on board"));
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
                .orElseThrow(() -> new IllegalStateException("King is not found on board"));
        return king.isCheckMate(this);
    }

}
