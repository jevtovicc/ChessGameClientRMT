package gui;

import client.Client;

import javax.swing.*;

public class GUIController implements Runnable{

    private static LoginWindow loginWindow;
    private static ConnectingWindow connectingWindow;
    private static GameWindow gameWindow;

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginWindow = new LoginWindow();
        connectingWindow = new ConnectingWindow();
        gameWindow = new GameWindow();
        showLoginWindow();
    }

    private void showLoginWindow() {
        loginWindow.setVisible(true);
    }

    private static void showConnectingWindow() {
        loginWindow.setVisible(false);
        connectingWindow.setVisible(true);
        Client.requestPlayersFromServer();
    }

    private static void showGameWindow() {
        connectingWindow.setVisible(false);
        gameWindow.setVisible(true);
    }

    public static void loginSuccess() {
        showConnectingWindow();
    }

    public static void loginFail() {
        JOptionPane.showMessageDialog(null, "Username already exists. Please choose another one", "Username not unique", JOptionPane.ERROR_MESSAGE);
    }

    public static void addPlayerToList(String playersUsernames) {
        connectingWindow.setOnlinePlayersList(playersUsernames);
    }

    public static void removePlayerFromList(String username) {
        connectingWindow.removePlayerFromList(username);
    }

    public static boolean showGameRequest(String sender) {
        int response = JOptionPane.showOptionDialog(null, sender + " invited you to play. Do you accept the request?", "Invitation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (response == JOptionPane.YES_OPTION) {
            showGameWindow();
        }
        return response == JOptionPane.YES_OPTION;
    }

    public static void showInvitationAccept() {
        showGameWindow();
    }

    public static void showInvitationReject(String rejecter) {
        JOptionPane.showMessageDialog(null, rejecter + " rejected your request. Try connecting with some other player or try again later", "Invitation rejected", JOptionPane.ERROR_MESSAGE);
    }
}
