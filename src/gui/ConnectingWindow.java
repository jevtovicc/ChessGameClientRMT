package gui;

import client.Client;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConnectingWindow extends JFrame {

    DefaultListModel dlm;
    JList<String> onlinePlayersList;
    JButton btnSendRequest;

    public ConnectingWindow() {
        setTitle("Connect with others");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close the application?",
                        "Close app",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    Client.disconnect();
                }
            }
        });

        JPanel pane = new JPanel();
        pane.add(new JLabel("Online players you can play with"));

        dlm = new DefaultListModel();
        onlinePlayersList = new JList<>(dlm);
        onlinePlayersList.addListSelectionListener(e ->
            btnSendRequest.setEnabled(!onlinePlayersList.isSelectionEmpty()));

        pane.add(onlinePlayersList);

        btnSendRequest = new JButton("Send request");
        btnSendRequest.setEnabled(false);
        btnSendRequest.addActionListener(e -> Client.sendGameRequest(onlinePlayersList.getSelectedValue()));
        pane.add(btnSendRequest);

        add(pane);
    }

    public void setOnlinePlayersList(String onlinePlayersList) {
        for (String username : onlinePlayersList.split(",")) {
            dlm.addElement(username);
        }
    }

    public void removePlayerFromList(String username) {
        dlm.removeElement(username);
        if (dlm.size() == 0) {
            btnSendRequest.setEnabled(false);
        }
    }
}
