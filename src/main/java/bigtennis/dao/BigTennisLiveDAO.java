package bigtennis.dao;

import bigtennis.entity.BigTennisLiveMatchList;
import server.dao.LiveDAO;

public class BigTennisLiveDAO extends LiveDAO {

    private static final String URL = "https://1xstavka.ru/live/Tennis/";

    BigTennisLiveMatchList liveMatchList;

    public BigTennisLiveDAO() {
        super(URL);
    }

    public BigTennisLiveMatchList getLiveMatchList() {
        return liveMatchList;
    }

    @Override
    public void updateLiveList() {
        liveMatchList = liveMatchBuilder.getBigTennisLiveMatchList(webElementProvider);
    }

}
