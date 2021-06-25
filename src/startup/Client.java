package startup;

import gui.GUIController;
import gui.GameWindow;
import gui.Position;
import piece.Piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private static BufferedReader inputFromServer;
    private static PrintStream outputToServer;
    private static Socket connectionSocket;
    private static String username;
    private static String opponentUsername;
    private static boolean isWhite;

    public static boolean isWhite() { return isWhite; }
    public static void setIsWhite(boolean flag) { isWhite = flag; }
    public static String getOpponentUsername() { return opponentUsername; }

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
                    /* OK indicates that username is unique */
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
                        String usernames = parts[1]; // can be multiple usernames that are comma separated
                        GUIController.addPlayerToList(usernames);
                    }
                }

                if (messageFromServer.startsWith("NewOnlinePlayer")) {
                    String username = messageFromServer.split("@")[1]; // single username
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

                if (messageFromServer.startsWith("InvitationAccept")) {
                    opponentUsername = messageFromServer.split("@")[1];
                    GUIController.showInvitationAccept();
                }

                if (messageFromServer.startsWith("InvitationReject")) {
                    GUIController.showInvitationReject(messageFromServer.split("@")[1]);
                }

                /* synchronize opponent's move on this board */
                if (messageFromServer.startsWith("MoveMade")) {
                    /* parts format: srcCol,srcRow,destCol,destRow */
                    String[] parts = messageFromServer.split("@")[1].split(",");
                    char srcCol = parts[0].charAt(0);
                    int srcRow = Integer.parseInt(parts[1]);
                    char destCol = parts[2].charAt(0);
                    int destRow = Integer.parseInt(parts[3]);

                    Position source = GameWindow.getBoardPane().getPositionAt(srcCol, srcRow);
                    Position destination = GameWindow.getBoardPane().getPositionAt(destCol, destRow);

                    Piece piece = source.getPiece().get();
                    piece.move(destination);
                    GUIController.changeGameWindowTitle(true);
                    GameWindow.getHistoryPane().pushMoveToHistory(piece, source, destination);
                    GameWindow.getBoardPane().toggleActionListeners(true);
                    /* if is check */
                    if (GameWindow.getBoardPane().calculateIfInDanger()) {
                        /* check if checkmate */
                        if (GameWindow.getBoardPane().calculateIfCheckmate()) {
                            sendGameOver();
                        }
                    }
                }

                if (messageFromServer.startsWith("GameWon")) {
                    opponentUsername = null;
                    GUIController.showWinningDialog();
                }

                if (messageFromServer.startsWith("GameLost")) {
                    opponentUsername = null;
                    GUIController.showLosingDialog();
                }

                if (messageFromServer.startsWith("OpponentDisconnected")) {
                    opponentUsername = null;
                    GUIController.showOpponentDisconnectedDialog();
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

    public static void disconnect() { outputToServer.println("quit@" + opponentUsername); }

    public static void makeMove(Position source, Position destination) {
        GameWindow.getBoardPane().toggleActionListeners(false);
        GUIController.changeGameWindowTitle(false);
        // opponentUsername, src-col,src-row,dest-col,dest-row
        outputToServer.println("MoveMade@" + opponentUsername + "," + source.getColumn() + ","
                + source.getRow() + "," + destination.getColumn() + "," + destination.getRow());
    }

    //@winner;loser
    public static void sendGameOver() { outputToServer.println("GameOver@" + opponentUsername + ";" + username); }
}
