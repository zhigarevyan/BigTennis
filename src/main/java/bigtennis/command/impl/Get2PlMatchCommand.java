package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import bigtennis.entity.MatchList;
import server.command.impl.BasicCommand;

public class Get2PlMatchCommand extends BasicCommand {

    @Override
    public MatchList execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        return dataController.get2PlayerMatches(
                Integer.parseInt(getParameter("quantity")),
                getParameter("player1Name"),
                getParameter("player2Name"),
                getParameter("league"),
                getParameter("courtType"));
    }
}
