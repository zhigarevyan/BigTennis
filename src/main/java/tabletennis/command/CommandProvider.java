package tabletennis.command;

import tabletennis.command.impl.*;
import server.command.impl.BasicCommand;
import server.command.impl.LoadPrevDataCommand;

import java.util.HashMap;

public class CommandProvider {

    private static CommandProvider instance;
    private final HashMap<CommandName, BasicCommand> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GET_2_PL_MATCH_COMMAND, new Get2PlMatchCommand());
        commands.put(CommandName.GET_2_PL_MATCH_COMMAND2, new Get2PlMatchCommand());
        commands.put(CommandName.GET_2_PL_MATCH_COMMAND3, new Get2PlMatchCommand());
        commands.put(CommandName.GET_2_PL_MATCH_COMMAND4, new Get2PlMatchCommand());
        commands.put(CommandName.GET_2_PL_MATCH_COMMAND5, new Get2PlMatchCommand());
        commands.put(CommandName.GET_2_PL_MATCH_COMMAND6, new Get2PlMatchCommand());
        commands.put(CommandName.GET_PL_MATCH_COMMAND, new GetPlMatchCommand());
        commands.put(CommandName.GET_PL_MATCH2_COMMAND, new GetPlMatchCommand());
        commands.put(CommandName.GET_PL_MATCH3_COMMAND, new GetPlMatchCommand());
        commands.put(CommandName.GET_PL_MATCH4_COMMAND, new GetPlMatchCommand());
        commands.put(CommandName.GET_ACCESS_COMMAND, new GetAccessCommand());
        commands.put(CommandName.GET_LAST_MATCH_DATE_COMMAND, new GetLastMatchDateCommand());
        commands.put(CommandName.GET_PLAYER_LIST_COMMAND, new GetPlayerListCommand());
        commands.put(CommandName.GET_LEAGUE_LIST_COMMAND, new GetLeagueListCommand());
        commands.put(CommandName.GET_USER_LIST_COMMAND, new GetUserListCommand());
        commands.put(CommandName.LOAD_PREV_DATA_COMMAND, new LoadPrevDataCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public BasicCommand getCommand(String commandName) {
        commandName = commandName.toUpperCase();
        CommandName enumName = CommandName.valueOf(commandName);

        return commands.get(enumName);
    }

}
