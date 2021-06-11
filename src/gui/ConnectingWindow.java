package gui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ConnectingWindow extends JFrame {

    DefaultListModel dlm;
    JList<String> onlinePlayersList;

    public ConnectingWindow() {
        setTitle("Connect with others");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();
        pane.add(new JLabel("Online players you can play with"));

        dlm = new DefaultListModel();
        onlinePlayersList = new JList<>(dlm);

        pane.add(onlinePlayersList);

        add(pane);
    }

    public void setOnlinePlayersList(String onlinePlayersList) {
        for (String username : onlinePlayersList.split(",")) {
            dlm.addElement(username);
        }
    }

    public void removePlayerFromList(String username) { dlm.removeElement(username); }
}
