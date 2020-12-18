package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import server.command.impl.BasicCommand;
import server.entity.impl.TransferBoolean;
import server.entity.impl.TransferElement;
import server.entity.impl.TransferString;

public class SignUpCommand extends BasicCommand {

    @Override
    public TransferElement execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        dataController.signUp(getParameter("name"),getParameter("key"));
        return new TransferBoolean(true);
    }
}
