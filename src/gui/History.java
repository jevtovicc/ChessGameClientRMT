package gui;

import piece.Pawn;
import piece.Piece;
import startup.Client;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class History extends JPanel {

    DefaultListModel<String> dlm;
    JList<String> movesHistory;

    public History() {
        setPreferredSize(new Dimension(250, 600));
        setMaximumSize(new Dimension(250, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GRAY);

        JLabel historyLabel = new JLabel("Moves history");
        historyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(historyLabel);

        dlm = new DefaultListModel<>();
        movesHistory = new JList<>(dlm);
        movesHistory.setFixedCellHeight(25);

        JScrollPane scrollPane = new JScrollPane(movesHistory, VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(scrollPane);
    }

    public void pushMoveToHistory(Piece piece, Position source, Position destination) {
        String player = (piece.getColor() == Piece.PieceColor.White && Client.isWhite()) ||
                (piece.getColor() == Piece.PieceColor.Black && !Client.isWhite()) ? "you" : Client.getOpponentUsername();
        dlm.addElement(player + ": " + (piece instanceof Pawn ? "Pawn" : piece.getClass().getSimpleName()) + " from (" + source.getColumn() + ", " +
                source.getRow() + ") to (" + destination.getColumn() + ", " + destination.getRow() + ")");
    }
}
