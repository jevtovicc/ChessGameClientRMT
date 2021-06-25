package gui;

import startup.Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindow extends JFrame {

    private final JTextField usernameTxtField;

    public LoginWindow() {
        setTitle("Log in");
        setSize(500, 300);
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