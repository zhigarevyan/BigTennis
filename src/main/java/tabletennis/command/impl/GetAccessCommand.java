package tabletennis.command.impl;

import server.entity.impl.TransferBoolean;
import tabletennis.controller.TableTennisDataController;
import server.command.impl.BasicCommand;

public class GetAccessCommand extends BasicCommand {
    @Override
    public TransferBoolean execute() {
        TableTennisDataController dataController = TableTennisDataController.getInstance();
        return new TransferBoolean(dataController.checkUserAccess(getParameter("user_id")));
    }
}
