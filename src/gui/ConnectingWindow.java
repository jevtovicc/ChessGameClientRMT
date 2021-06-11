package gui;

import javax.swing.*;

public class ConnectingWindow extends JFrame {

    public ConnectingWindow() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();
        pane.add(new JButton("Klikni me"));

        add(pane);
    }
}
