package bigtennis.impl;

import controller.DataController;
import controller.connection.entity.impl.TransferStringList;

public class GetLeagueListCommand extends BasicCommand {

    @Override
    public TransferStringList execute() {
        DataController dataController = DataController.getInstance();
        return new TransferStringList(dataController.getLeagues());
    }
}
