package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import server.command.impl.BasicCommand;
import server.entity.impl.TransferElement;

public class GetUserCommand extends BasicCommand {

    @Override
    public TransferElement execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        return dataController.getUser(getParameter("key"));
    }
}
