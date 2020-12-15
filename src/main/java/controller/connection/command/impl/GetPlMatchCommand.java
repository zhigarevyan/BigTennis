package controller.connection.command.impl;

import controller.DataController;
import entity.MatchList;

public class GetPlMatchCommand extends BasicCommand {

    @Override
    public MatchList execute() {
        DataController dataController = DataController.getInstance();
        return dataController.getPlayerMatches(
                Integer.parseInt(getParameter("quantity")),
                getParameter("playerName"),
                getParameter("league"),
                getParameter("courtType"));
    }

}
