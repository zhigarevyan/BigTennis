package tabletennis.controller;

import bigtennis.controller.BigTennisClientController;
import bigtennis.entity.MatchList;
import server.controller.TransferObjectIO;
import tabletennis.command.CommandBuilder;
import server.entity.TransferObject;

import java.io.IOException;

public class TableTennisClientController {

    private final CommandBuilder commandBuilder = CommandBuilder.getInstance();
    private final TransferObjectIO transferObjectIO = new TransferObjectIO();

    private TransferObject transferObject;

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

    public void getLeagueList() {
        transferObject.addCommand(commandBuilder.getLeagueListCommand());
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
        transferObjectIO.init();
        transferObject = new TransferObject();
    }

    private void close() throws IOException {
        transferObjectIO.close();
    }

    public TransferObject execute() throws InterruptedException, IOException, ClassNotFoundException {
        transferObjectIO.send(transferObject);
        Thread.sleep(500);
        transferObject = transferObjectIO.get();
        close();
        return transferObject;
    }

}
