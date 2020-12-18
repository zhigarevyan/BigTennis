package tabletennis.command.impl;

import tabletennis.controller.TableTennisDataController;
import tabletennis.entity.MatchList;
import server.command.impl.BasicCommand;

public class GetPlMatchCommand extends BasicCommand {

    @Override
    public MatchList execute() {
        TableTennisDataController dataController = TableTennisDataController.getInstance();
        return dataController.getPlayerMatches(
                Integer.parseInt(getParameter("quantity")),
                getParameter("playerName"),
                getParameter("league"));
    }

}
