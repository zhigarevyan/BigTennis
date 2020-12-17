package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import server.command.impl.BasicCommand;
import server.entity.impl.TransferString;

public class GetLastMatchDateCommand extends BasicCommand {

    @Override
    public TransferString execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        return new TransferString(dataController.getLastMatchDate());
    }
}
