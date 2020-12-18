package bigtennis.controller;

import bigtennis.command.CommandBuilder;
import bigtennis.entity.MatchList;
import bigtennis.entity.User;
import server.controller.TransferObjectIO;
import server.entity.TransferObject;

import java.io.IOException;

public class BigTennisClientController {

    private final CommandBuilder commandBuilder = CommandBuilder.getInstance();

    private final TransferObjectIO transferObjectIO = new TransferObjectIO();

    private TransferObject transferObject;

    public void getPlayerList() {
        transferObject.addCommand(commandBuilder.getPlayerListCommand());
    }

    public void getPlayersMatches(int quantity, String playerName, String league, String courtType) {
        transferObject.addCommand(commandBuilder.getPlMatchCommand(quantity,playerName,league,courtType));
    }

    public void getPlayersMatches2(int quantity, String playerName, String league, String courtType) {
        transferObject.addCommand(commandBuilder.getPlMatch2Command(quantity,playerName,league,courtType));
    }

    public void get2PlayersMatches(int quantity, String player1Name, String player2Name, String league, String courtType) {
        transferObject.addCommand(commandBuilder.get2PlayersMatchesCommand(quantity,player1Name,player2Name,league,courtType));
    }

    public void sendNickname(String nickname, String android_id){
        transferObject.addCommand(commandBuilder.sendNicknameCommand(nickname, android_id));
    }

    public void getLeagueList() {
        transferObject.addCommand(commandBuilder.getLeagueListCommand());
    }

    public void getCourtList() {
        transferObject.addCommand(commandBuilder.getCourtListCommand());
    }

    public void checkUserAccess(String userID) {
        transferObject.addCommand(commandBuilder.checkUserAccessCommand(userID));
    }

    public void signup(String nickname, String androidID) {
        transferObject.addCommand(commandBuilder.signUpCommand(nickname,androidID));
    }

    public void getUser(String key) {
        transferObject.addCommand(commandBuilder.getUserCommand(key));
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

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        BigTennisClientController controller = new BigTennisClientController();
        controller.init();
        controller.getPlayersMatches(10,"Анней Ласка","","");
        controller.get2PlayersMatches(10,"Анней Ласка","Жан Скиндер","","");
        controller.getUser("id");
        TransferObject transferObject = controller.execute();
        MatchList matchList = transferObject.get(0).getAsBigTennisMatchList();
        MatchList matchList2 = transferObject.get(1).getAsBigTennisMatchList();
        User user = transferObject.get(2).getAsUser();
        System.out.println(matchList);
        System.out.println(matchList2);
        System.out.println(user);
    }

}
