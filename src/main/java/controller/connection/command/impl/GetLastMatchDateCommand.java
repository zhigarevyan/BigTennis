package controller.connection.command.impl;

import controller.DataController;
import controller.connection.entity.impl.TransferString;

public class GetLastMatchDateCommand extends BasicCommand {

    @Override
    public TransferString execute() {
        DataController dataController = DataController.getInstance();
        return new TransferString(dataController.getLastMatchDate());
    }
}
