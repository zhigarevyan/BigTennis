package tabletennis.command.impl;

import tabletennis.controller.TableTennisDataController;
import tabletennis.entity.MatchList;
import server.command.impl.BasicCommand;

public class Get2PlMatchCommand extends BasicCommand {

    @Override
    public MatchList execute() {
        TableTennisDataController dataController = TableTennisDataController.getInstance();
        return dataController.get2PlayerMatches(
                Integer.parseInt(getParameter("quantity")),
                getParameter("player1Name"),
                getParameter("player2Name"),
                getParameter("league"));
    }
}
