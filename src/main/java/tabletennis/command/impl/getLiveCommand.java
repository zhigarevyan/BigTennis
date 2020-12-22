package tabletennis.command.impl;

import server.command.impl.BasicCommand;
import server.dao.LiveDAO;
import server.dao.LiveDAOProvider;
import server.entity.LiveMatchList;
import tabletennis.entity.TableTennisLiveMatchList;

public class getLiveCommand extends BasicCommand {
    @Override
    public TableTennisLiveMatchList execute() {
        LiveDAOProvider liveDAOProvider = LiveDAOProvider.getInstance();
        return liveDAOProvider.getTableTennisDAO().getLiveMatchList();
    }
}
