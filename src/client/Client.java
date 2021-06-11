package client;

import gui.ConnectingWindow;
import gui.GUIController;
import gui.LoginWindow;

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

    public static void main(String[] args) {

        final int PORT = 6666;
        final String HOST_NAME = "localhost";

        try {
            connectionSocket = new Socket(HOST_NAME, PORT);
            initializeIO();

            new Thread(new GUIController()).start();

            while (true) {
                String messageFromServer = inputFromServer.readLine();

                if (messageFromServer == null) break;

                if (messageFromServer.startsWith("Goodbye")) {
                    break;
                }

                if (messageFromServer.startsWith("Username")) {
                    String status = messageFromServer.split("@")[1];
                    if (status.equals("OK")) {
                        GUIController.loginSuccess();
                    } else if (status.equals("NOT_UNIQUE")) {
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
            }

            connectionSocket.close();
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

}
