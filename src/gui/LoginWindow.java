package gui;

import client.Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginWindow extends JFrame {

    private JTextField usernameTxtField;

    public LoginWindow() {
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();

        JLabel usernameLabel = new JLabel("Enter username");
        usernameTxtField = new JTextField();
        usernameTxtField.setColumns(20);

        pane.add(usernameLabel);
        pane.add(usernameTxtField);

        JButton loginBtn = new JButton("Log in");
        loginBtn.addActionListener(e -> {
            String username = usernameTxtField.getText();
            if (!username.isBlank()) {
                Client.sendUsernameToServer(username);
            } else {
                usernameTxtField.setBorder(new LineBorder(Color.RED, 2));
            }
        });
        pane.add(loginBtn);

        add(pane);
    }
}