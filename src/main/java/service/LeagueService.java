package service;

import dao.LeagueDAO;
import entity.dbEntity.LeagueEntity;

import java.util.List;

public class LeagueService {
    private LeagueDAO leagueDao = new LeagueDAO();

    public LeagueService() {
    }

    public LeagueEntity byId(int id) {
        return leagueDao.findById(id);
    }

    public void save(LeagueEntity league) {
        leagueDao.save(league);
    }

    public void delete(LeagueEntity league) {
        leagueDao.delete(league);
    }

    public void update(LeagueEntity league) {
        leagueDao.update(league);
    }

    public List<LeagueEntity> all() {
        return leagueDao.findAll();
    }

    public LeagueEntity getOrNewByName(String name) {
        return new LeagueEntity();
    }

    public LeagueEntity byName(String name) {
        List<LeagueEntity> leaguesList = leagueDao.byName(name);
        if (leaguesList.size() == 0) {
            return null;
        }
        return leaguesList.get(0);
    }

}
