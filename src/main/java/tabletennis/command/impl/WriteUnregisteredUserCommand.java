package tabletennis.command.impl;

import server.entity.impl.TransferBoolean;
import tabletennis.controller.TableTennisDataController;
import server.command.impl.BasicCommand;

public class WriteUnregisteredUserCommand extends BasicCommand {
    @Override
    public TransferBoolean execute() {
        TableTennisDataController dataController = TableTennisDataController.getInstance();
        return new TransferBoolean(dataController.addUnregisteredUser(getParameter("nickname"),getParameter("user_id")));
    }
}
