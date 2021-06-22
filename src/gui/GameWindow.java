package gui;

import startup.Client;
import piece.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    static Board boardPane;
    static History historyPane;

    public static History getHistoryPane() { return historyPane; }
    public static Board getBoardPane() { return boardPane; }

    public GameWindow() {
        setTitle("Chess game");
        setLayout(new FlowLayout());
        setSize(1300, 700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close the application? If you do, you will lose this game!",
                        "Close app",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    Client.disconnect();
                }
            }
        });

        boardPane = new Board();
        add(boardPane);

        historyPane = new History();
        add(historyPane);
    }

    @Override
    public Insets getInsets() {
        return new Insets(50, 0, 10, 0);
    }

}
