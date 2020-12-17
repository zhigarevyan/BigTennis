package server.command.impl;

import controller.CollectingController;
import server.entity.impl.TransferElement;

public class LoadPrevDataCommand extends BasicCommand {

    @Override
    public TransferElement execute() {
        CollectingController collectingController = CollectingController.getInstance();
        collectingController.collectPrevious(3);

        return null;
    }
}
