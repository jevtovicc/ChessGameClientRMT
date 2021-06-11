package gui;

import javax.swing.*;

public class GUIController implements Runnable{

    private static LoginWindow loginWindow;
    private static ConnectingWindow connectingWindow;


    @Override
    public void run() {

        loginWindow = new LoginWindow();
        connectingWindow = new ConnectingWindow();

        showLoginWindow();


    }

    private void showLoginWindow() {
        loginWindow.setVisible(true);
    }

    private static void showConnectingWindow() {
        loginWindow.setVisible(false);
        connectingWindow.setVisible(true);
    }

    public static void loginSuccess() {
        showConnectingWindow();
    }

    public static void loginFail() {
        JOptionPane.showMessageDialog(null, "Username already exists. Please choose another one", "Username not unique", JOptionPane.ERROR_MESSAGE);
    }
}
