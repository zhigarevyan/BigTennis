package tabletennis.dao;

import server.dao.LiveDAO;
import server.entity.LiveMatchList;
import tabletennis.entity.TableTennisLiveMatchList;

public class TableTennisLiveDAO extends LiveDAO {

        private static final String URL = "https://1xstavka.ru/live/Tennis/";

        TableTennisLiveMatchList tableTennisLiveMatchList;

        public TableTennisLiveDAO() {
            super(URL);
        }

        public TableTennisLiveMatchList getLiveMatchList() {
            return tableTennisLiveMatchList;
        }

        @Override
        public void updateLiveList() {
            tableTennisLiveMatchList = liveMatchBuilder.getTableTennisLiveMatchList(webElementProvider);
        }
}
