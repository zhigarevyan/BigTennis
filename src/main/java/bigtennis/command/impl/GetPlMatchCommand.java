package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import server.command.impl.BasicCommand;
import bigtennis.entity.MatchList;

public class GetPlMatchCommand extends BasicCommand {

    @Override
    public MatchList execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        return dataController.getPlayerMatches(
                Integer.parseInt(getParameter("quantity")),
                getParameter("playerName"),
                getParameter("league"),
                getParameter("courtType"));
    }

}
