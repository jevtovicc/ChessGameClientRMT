package gui;

import startup.Client;

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
        showLoginWindow();
    }

    private void showLoginWindow() {
        loginWindow.setVisible(true);
    }

    private static void showConnectingWindow() {
        loginWindow.setVisible(false);
        if (gameWindow != null) {
            gameWindow.setVisible(false);
            gameWindow = null;
        }
        connectingWindow.setVisible(true);
        Client.requestPlayersFromServer();
    }

    private static void showGameWindow() {
        connectingWindow.clearOnlinePlayersList();
        gameWindow = new GameWindow();
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
            Client.setIsWhite(true);
            showGameWindow();
        }
        return response == JOptionPane.YES_OPTION;
    }

    public static void showInvitationAccept() {
        Client.setIsWhite(false);
        showGameWindow();
    }

    public static void showInvitationReject(String rejecter) {
        JOptionPane.showMessageDialog(null, rejecter + " rejected your request. Try connecting with some other player or try again later", "Invitation rejected", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWinningDialog() {
        JOptionPane.showMessageDialog(null, "Checkmate! Congratulations, you won the game!");
        showConnectingWindow();
    }

    public static void showLosingDialog() {
        JOptionPane.showMessageDialog(null, "Checkmate! You lost this game... Better luck next time!");
        showConnectingWindow();
    }

    public static void showOpponentDisconnectedDialog() {
        JOptionPane.showMessageDialog(null, "Your opponent disconnected! You won this game!");
        showConnectingWindow();
    }

    public static void changeGameWindowTitle(boolean onMove) {
        gameWindow.changeTitle(onMove);
    }

    public static void exitProgram() { System.exit(0); }
}
