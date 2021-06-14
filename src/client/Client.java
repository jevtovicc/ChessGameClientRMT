package client;

import gui.GUIController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    static BufferedReader inputFromServer;
    static PrintStream outputToServer;
    static Socket connectionSocket;
    static String username;
    static String opponentUsername;

    public static void main(String[] args) {

        final int PORT = 6666;
        final String HOST_NAME = "localhost";

        try {
            connectionSocket = new Socket(HOST_NAME, PORT);
            initializeIO();

            new Thread(new GUIController()).start();

            while (true) {
                String messageFromServer = inputFromServer.readLine();

                if (messageFromServer == null) {
                    System.out.println("Server is down! Disconnecting you...");
                    break;
                }

                if (messageFromServer.startsWith("Goodbye")) break;

                if (messageFromServer.startsWith("Username")) {
                    String status = messageFromServer.split("@")[1];
                    if (status.startsWith("OK")) {
                        username = status.split(";")[1];
                        GUIController.loginSuccess();
                    } else if (status.startsWith("NOT_UNIQUE")) {
                        GUIController.loginFail();
                    }
                }

                else if (messageFromServer.startsWith("OnlinePlayers")) {
                    String[] parts = messageFromServer.split("@");
                    if (parts.length > 1) {
                        String username = parts[1];
                        GUIController.addPlayerToList(username);
                    }
                }

                if (messageFromServer.startsWith("NewOnlinePlayer")) {
                    String username = messageFromServer.split("@")[1];
                    GUIController.addPlayerToList(username);
                }

                if (messageFromServer.startsWith("PlayerDisconnected")) {
                    String username = messageFromServer.split("@")[1];
                    GUIController.removePlayerFromList(username);
                }

                if (messageFromServer.startsWith("GameRequest")) {
                    String sender = messageFromServer.split("@")[1];
                    // if request was accepted
                    if (GUIController.showGameRequest(sender)) {
                        opponentUsername = sender;
                        acceptGameRequest();
                    } else {
                        rejectGameRequest(sender);
                    }
                }

                if (messageFromServer.startsWith("InvitationAccept@")) {
                    GUIController.showInvitationAccept();
                }

                if (messageFromServer.startsWith("InvitationReject@")) {
                    GUIController.showInvitationReject(messageFromServer.split("@")[1]);
                }


            }

            connectionSocket.close();
            System.out.println("Connection closed");
            GUIController.exitProgram();
        } catch (IOException ex) {
            System.out.println("Server is down... Try again later");
        }

    }

    private static void initializeIO() {
        try {
            inputFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            outputToServer = new PrintStream(connectionSocket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendUsernameToServer(String username) {
        outputToServer.println("Username@" + username);
    }

    public static void requestPlayersFromServer() { outputToServer.println("OnlinePlayers"); }

    public static void sendGameRequest(String receiver) {
        outputToServer.println("GameRequest@" + username + "," + receiver);
    }

    public static void acceptGameRequest() {
        outputToServer.println("InvitationAccept@" + username + "," + opponentUsername);
    }

    public static void rejectGameRequest(String sender) {
        outputToServer.println("InvitationReject@" + username + "," + sender);
    }

    public static void disconnect() { outputToServer.println("quit"); }
}
