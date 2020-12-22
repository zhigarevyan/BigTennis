package bigtennis.command.impl;

import bigtennis.entity.BigTennisLiveMatchList;
import server.command.impl.BasicCommand;
import server.dao.LiveDAOProvider;
import tabletennis.entity.TableTennisLiveMatchList;

public class GetLiveCommand extends BasicCommand {
    @Override
    public BigTennisLiveMatchList execute() {
        LiveDAOProvider liveDAOProvider = LiveDAOProvider.getInstance();
        return liveDAOProvider.getBigTennisDAO().getLiveMatchList();
    }
}
