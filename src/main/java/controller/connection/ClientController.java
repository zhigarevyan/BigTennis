package controller.connection;

import controller.connection.command.CommandBuilder;
import controller.connection.entity.TransferObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientController {

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final CommandBuilder commandBuilder = CommandBuilder.getInstance();
    //private final String host = "130.193.46.131";
    private final String host = "178.154.224.83";
    private final int port = 8030;
    private Socket clientSocket;
    private TransferObject transferObject;

    private void sendTransferObject(TransferObject transferObject) throws IOException {
        objectOutputStream.writeObject(transferObject);
        objectOutputStream.flush();
    }

    private TransferObject getTransferObject() throws IOException, ClassNotFoundException {
        return (TransferObject) objectInputStream.readObject();
    }

    public void getPlayerList() {
        transferObject.addCommand(commandBuilder.getPlayerListCommand());
    }

    public void getPlayersMatches(int quantity, String playerName, String league) {
        transferObject.addCommand(commandBuilder.getPlMatchCommand(quantity,playerName,league));
    }

    public void getPlayersMatches2(int quantity, String playerName, String league) {
        transferObject.addCommand(commandBuilder.getPlMatch2Command(quantity,playerName,league));
    }
    public void getPlayersMatches3(int quantity, String playerName, String league) {
        transferObject.addCommand(commandBuilder.getPlMatch3Command(quantity,playerName,league));
    }
    public void getPlayersMatches4(int quantity, String playerName, String league) {
        transferObject.addCommand(commandBuilder.getPlMatch4Command(quantity,playerName,league));
    }

    public void get2PlayersMatches(int quantity, String player1Name, String player2Name, String league) {
        transferObject.addCommand(commandBuilder.get2PlayersMatchesCommand(quantity,player1Name,player2Name,league));
    }
    public void get2PlayersMatches2(int quantity, String player1Name, String player2Name, String league) {
        transferObject.addCommand(commandBuilder.get2PlayersMatchesCommand2(quantity,player1Name,player2Name,league));
    }
    public void get2PlayersMatches3(int quantity, String player1Name, String player2Name, String league) {
        transferObject.addCommand(commandBuilder.get2PlayersMatchesCommand3(quantity,player1Name,player2Name,league));
    }
    public void get2PlayersMatches4(int quantity, String player1Name, String player2Name, String league) {
        transferObject.addCommand(commandBuilder.get2PlayersMatchesCommand4(quantity,player1Name,player2Name,league));
    }
    public void get2PlayersMatches5(int quantity, String player1Name, String player2Name, String league) {
        transferObject.addCommand(commandBuilder.get2PlayersMatchesCommand5(quantity,player1Name,player2Name,league));
    }
    public void get2PlayersMatches6(int quantity, String player1Name, String player2Name, String league) {
        transferObject.addCommand(commandBuilder.get2PlayersMatchesCommand6(quantity,player1Name,player2Name,league));
    }

    public void sendNickname(String nickname, String android_id){
        transferObject.addCommand(commandBuilder.sendNicknameCommand(nickname, android_id));
    }

    public void getLeagues() {
        transferObject.addCommand(commandBuilder.getLeaguesCommand());
    }

    public void checkUserAccess(String user_id) {
        transferObject.addCommand(commandBuilder.checkUserAccessCommand(user_id));
    }

    public void getUsersList() {
        transferObject.addCommand(commandBuilder.getUsersListCommand());
    }

    public void getLastMatchDate() {
        transferObject.addCommand(commandBuilder.getLastMatchDate());
    }

    public void init() throws IOException {
        clientSocket = new Socket(host,port);
        objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
        objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        transferObject = new TransferObject();
    }

    public TransferObject execute() throws InterruptedException, IOException, ClassNotFoundException {
        sendTransferObject(transferObject);
        Thread.sleep(500);
        transferObject = getTransferObject();
        close();
        return transferObject;
    }

    private void close() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
        clientSocket.close();
    }
}
