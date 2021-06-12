package gui;

import client.Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginWindow extends JFrame {

    private JTextField usernameTxtField;

    public LoginWindow() {
        setTitle("Log in");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();

        JLabel usernameLabel = new JLabel("Enter username");
        usernameTxtField = new JTextField();
        usernameTxtField.setColumns(10);
        usernameTxtField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    submitUsername();
                }
            }
        });

        pane.add(usernameLabel);
        pane.add(usernameTxtField);

        JButton loginBtn = new JButton("Log in");
        loginBtn.addActionListener(e -> submitUsername());
        pane.add(loginBtn);

        add(pane);
    }

    private void submitUsername() {
        String username = usernameTxtField.getText();
        if (!username.isBlank()) {
            Client.sendUsernameToServer(username);
        } else {
            usernameTxtField.setBorder(new LineBorder(Color.RED, 2));
        }
    }
}