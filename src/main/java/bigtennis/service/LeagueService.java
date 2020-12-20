package bigtennis.service;

import bigtennis.dao.LeagueDAO;
import bigtennis.entity.dbEntity.LeagueEntity;

import java.util.HashMap;
import java.util.List;

public class LeagueService {
    private LeagueDAO leagueDao = new LeagueDAO();

    private HashMap<String, LeagueEntity> leagueMap;


    public LeagueService() {
        updateLeagueMap();
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

    public LeagueEntity fromMap(String name) {
        return leagueMap.get(name);
    }

    private void updateLeagueMap() {
        leagueMap = new HashMap<>();
        List<LeagueEntity> leagueEntityList = leagueDao.findAll();

        leagueEntityList.forEach(playerEntity -> leagueMap.put(playerEntity.getName(),playerEntity));
    }

    public LeagueEntity getOrNewByName(String name) {
        LeagueEntity league = fromMap(name);
        if (league != null) {
            return league;
        }

        LeagueEntity newLeague = new LeagueEntity();

        newLeague.setName(name);
        save(newLeague);

        updateLeagueMap();
        return fromMap(name);
    }

    public LeagueEntity byName(String name) {
        List<LeagueEntity> leaguesList = leagueDao.byName(name);
        if (leaguesList.size() == 0) {
            return null;
        }
        return leaguesList.get(0);
    }

}
