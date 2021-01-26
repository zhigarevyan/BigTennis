package server.dao;

import bigtennis.dao.BigTennisLiveDAO;
import tabletennis.dao.TableTennisLiveDAO;

public class LiveDAOProvider {

    public static final LiveDAOProvider instance = new LiveDAOProvider();

    private final BigTennisLiveDAO bigTennisDAO = new BigTennisLiveDAO();
    private final TableTennisLiveDAO tableTennisDAO = new TableTennisLiveDAO();

    private LiveDAOProvider() {
    }

    public static LiveDAOProvider getInstance() {
        return instance;
    }

    public TableTennisLiveDAO getTableTennisDAO() {
        return tableTennisDAO;
    }

    public BigTennisLiveDAO getBigTennisDAO() {
        return bigTennisDAO;
    }
}
