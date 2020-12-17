package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import server.command.impl.BasicCommand;
import server.entity.impl.TransferStringList;

public class GetPlayerListCommand extends BasicCommand {

    @Override
    public TransferStringList execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        return new TransferStringList(dataController.getAllPlayerNames());
    }

}
