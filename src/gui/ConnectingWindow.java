package gui;

import startup.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class ConnectingWindow extends JFrame {

    DefaultListModel<String> dlm;
    JList<String> onlinePlayersList;
    JButton btnSendRequest;

    public ConnectingWindow() {
        setTitle("Connect with others");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
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
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        JLabel label = new JLabel("Online players you can play with");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(label);

        dlm = new DefaultListModel<>();
        onlinePlayersList = new JList<>(dlm);
        onlinePlayersList.addListSelectionListener(e ->
            btnSendRequest.setEnabled(!onlinePlayersList.isSelectionEmpty()));
        onlinePlayersList.setFixedCellWidth(400);
        onlinePlayersList.setFixedCellHeight(25);

        JScrollPane scrollPane = new JScrollPane(onlinePlayersList, VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMaximumSize(new Dimension(450, 250));
        scrollPane.setPreferredSize(new Dimension(450, 250));
        pane.add(scrollPane);

        btnSendRequest = new JButton("Send request");
        btnSendRequest.setEnabled(false);
        btnSendRequest.addActionListener(e -> Client.sendGameRequest(onlinePlayersList.getSelectedValue()));
        btnSendRequest.setAlignmentX(Component.CENTER_ALIGNMENT);
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
    }

    public void clearOnlinePlayersList() {
        dlm.removeAllElements();
    }
}
