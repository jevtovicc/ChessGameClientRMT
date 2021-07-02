package gui;

import gui.boardpopulator.BlackBoardPopulator;
import gui.boardpopulator.WhiteBoardPopulator;
import piece.*;
import startup.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {

    private final List<Position> positions; /* positions on the board */

    public List<Position> getPositions() { return positions; }

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

    /* enable action listeners on all positions that contain pieces which belong to player (based on color)
    and all empty positions */
    public void enableActionListeners() {
        for (Position position : positions) {
            position.getPiece()
                    .ifPresentOrElse(piece -> {
                        if (piece.getColor() == (Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black)) {
                            position.addActionListener(position);
                        }
                    }, () ->  position.addActionListener(position));
        }
    }

    public void disableActionListeners() {
        positions.forEach(position -> position.removeActionListener(position));
    }

    /* find king that corresponds to player's color and call it's isInDanger method */
    public boolean calculateIfInDanger() {
        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(position -> position.getPiece().isPresent() && position.getPiece().get().getColor() == pieceColor)
                .map(position -> position.getPiece().get())
                .filter(piece -> piece instanceof King)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("King is not found on board"));
        return king.isInDanger(this);
    }

    /* find king that corresponds to player's color and call it's isCheckMate method */
    public boolean calculateIfCheckmate() {
        Piece.PieceColor pieceColor = Client.isWhite() ? Piece.PieceColor.White : Piece.PieceColor.Black;
        King king = (King) positions.stream()
                .filter(position -> position.getPiece().isPresent() && position.getPiece().get().getColor() == pieceColor)
                .map(position -> position.getPiece().get())
                .filter(piece -> piece instanceof King)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("King is not found on board"));
        return king.isCheckMate(this);
    }

}
