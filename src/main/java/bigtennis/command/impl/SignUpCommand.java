package bigtennis.command.impl;

import bigtennis.controller.BigTennisDataController;
import server.command.impl.BasicCommand;
import server.entity.impl.TransferBoolean;

public class SignUpCommand extends BasicCommand {

    @Override
    public TransferBoolean execute() {
        BigTennisDataController dataController = BigTennisDataController.getInstance();
        dataController.signUp(getParameter("name"),getParameter("key"));
        return new TransferBoolean(true);
    }
}
