package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import server.command.impl.BasicCommand;
import server.entity.impl.TransferBoolean;

public class GetAccessCommand extends BasicCommand {
    @Override
    public TransferBoolean execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        return new TransferBoolean(dataController.checkUserAccess(getParameter("user_id")));
    }
}
