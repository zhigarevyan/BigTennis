package bigtennis.command;

import server.command.Command;
import server.command.impl.BasicCommand;

public class CommandBuilder {

    private static CommandBuilder instance;
    private CommandProvider commandProvider = new CommandProvider();

    private CommandBuilder() {
    }

    public static CommandBuilder getInstance() {
        if (instance == null) {
            instance = new CommandBuilder();
        }
        return instance;
    }

    public BasicCommand getPlayerListCommand() {
        BasicCommand command = commandProvider.getCommand("get_player_list_command");
        return command;
    }

    public BasicCommand getPlMatchCommand(int quantity, String playerName, String league, String courtType) {
        BasicCommand command = commandProvider.getCommand("get_pl_match_command");
        command.putParameter("quantity", String.valueOf(quantity));
        command.putParameter("playerName",playerName);
        command.putParameter("league",league);
        command.putParameter("courtType",courtType);

        return command;
    }

    public BasicCommand getPlMatch2Command(int quantity, String playerName, String league, String courtType) {
        BasicCommand command = commandProvider.getCommand("get_pl_match2_command");
        command.putParameter("quantity", String.valueOf(quantity));
        command.putParameter("playerName",playerName);
        command.putParameter("league",league);
        command.putParameter("courtType",courtType);

        return command;
    }

    public BasicCommand get2PlayersMatchesCommand(int quantity, String player1Name, String player2Name, String league, String courtType) {
        BasicCommand command = commandProvider.getCommand("get_2_pl_match_command");
        command.putParameter("quantity", String.valueOf(quantity));
        command.putParameter("player1Name",player1Name);
        command.putParameter("player2Name",player2Name);
        command.putParameter("league",league);
        command.putParameter("courtType",courtType);

        return command;
    }

    public BasicCommand getLeagueListCommand() {
        BasicCommand command = commandProvider.getCommand("get_league_list_command");
        return command;
    }

    public BasicCommand getCourtListCommand() {
        BasicCommand command = commandProvider.getCommand("get_court_list_command");
        return command;
    }

    public BasicCommand checkUserAccessCommand(String user_id) {
        BasicCommand command = commandProvider.getCommand("get_access_command");
        command.putParameter("user_id",user_id);
        return command;
    }

    public BasicCommand getUsersListCommand() {
        BasicCommand command = commandProvider.getCommand("get_user_list_command");
        return command;
    }

    public BasicCommand getLastMatchDate() {
        BasicCommand command = commandProvider.getCommand("get_last_match_date_command");
        return command;
    }

    public BasicCommand sendNicknameCommand(String nickname, String androidID){
        BasicCommand command = commandProvider.getCommand("SEND_NICKNAME_COMMAND");
        command.putParameter("nickname",nickname);
        command.putParameter("android_id",androidID);
        return command;
    }

    public Command signUpCommand(String name, String key) {
        BasicCommand command = commandProvider.getCommand("sign_up_command");
        command.putParameter("name",name);
        command.putParameter("key",key);
        return command;
    }

    public Command getUserCommand(String key) {
        BasicCommand command = commandProvider.getCommand("get_user_command");
        command.putParameter("key",key);
        return command;
    }
}
