package gui;

import client.Client;

import javax.swing.*;

public class ConnectingWindow extends JFrame {

    DefaultListModel dlm;
    JList<String> onlinePlayersList;
    JButton btnSendRequest;

    public ConnectingWindow() {
        setTitle("Connect with others");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
