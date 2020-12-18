package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import bigtennis.entity.User;
import server.command.impl.BasicCommand;

public class GetUserCommand extends BasicCommand {

    @Override
    public User execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        return dataController.getUser(getParameter("key"));
    }
}
