package tabletennis.command.impl;

import server.entity.impl.TransferString;
import tabletennis.controller.TableTennisDataController;
import server.command.impl.BasicCommand;
import server.entity.impl.TransferElement;

public class GetLastMatchDateCommand extends BasicCommand {

    @Override
    public TransferElement execute() {
        TableTennisDataController dataController = TableTennisDataController.getInstance();
        return new TransferString(dataController.getLastMatchDate());
    }
}
