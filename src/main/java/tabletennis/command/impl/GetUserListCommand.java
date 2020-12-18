package tabletennis.command.impl;

import server.entity.impl.TransferStringList;
import tabletennis.controller.TableTennisDataController;
import server.command.impl.BasicCommand;

public class GetUserListCommand extends BasicCommand {

    @Override
    public TransferStringList execute() {
        TableTennisDataController dataController = TableTennisDataController.getInstance();
        return new TransferStringList(dataController.getUsers());
    }

}
