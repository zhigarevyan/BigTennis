package bigtennis.service;

import bigtennis.dao.MatchDAO;
import bigtennis.entity.MatchList;
import bigtennis.entity.dbEntity.*;

import java.util.List;

public class MatchService {

    private final int P1_WIN_VALUE = 1;
    private final int P2_WIN_VALUE = 2;


    private MatchDAO matchDao = new MatchDAO();

    public MatchService() {
    }

    public MatchEntity byId(int id) {
        return matchDao.findById(id);
    }

    public void save(MatchEntity match) {
        matchDao.save(match);
    }

    public void delete(MatchEntity match) {
        matchDao.delete(match);
    }

    public void update(MatchEntity match) {
        matchDao.update(match);
    }

    public List<MatchEntity> all() {
        return matchDao.findAll();
    }

    public MatchList getMatches(int quantity, String league, String courtType) {
        return new MatchList(matchDao.getLastMatches(quantity, league, courtType));
    }

    public MatchList get2PlMatches(int quantity, String p1name, String p2name, String league, String courtType) {
        List<MatchEntity> listMLEntity = matchDao.get2PlMatches(quantity, p1name, p2name, league, courtType);

        MatchList matchList = new MatchList(listMLEntity);
        matchList.sortByP1(p1name);

        return matchList;
    }

    public MatchList getPlMatches(int quantity, String p1name, String league, String courtType) {
        MatchList matchList = new MatchList(matchDao.getPlMatches(quantity, p1name, league, courtType));
        matchList.sortByP1(p1name);
        return matchList;
    }

    public MatchEntity byParams(String p1name, String p2name, String score, String set1, String set2, String set3, String set4, String set5, String date, String league, String courtType) {
        List<MatchEntity> matchesLList = matchDao.byParams(p1name, p2name, score, set1, set2, set3, set4, set5, date, league, courtType);
        if (matchesLList.size() == 0) {
            return null;
        }
        return matchesLList.get(0);
    }

    public MatchEntity byId(PlayerEntity player1, PlayerEntity player2, ResultEntity result, String date, LeagueEntity league, CourtTypeEntity courtType) {
        List<MatchEntity> matchesLList = matchDao.byId(player1, player2, result, date, league, courtType);
        if (matchesLList.size() == 0) {
            return null;
        }
        return matchesLList.get(0);
    }

    public void insertIgnore(PlayerEntity player1, PlayerEntity player2, ResultEntity result, String date, LeagueEntity league, CourtTypeEntity courtType) {
        MatchEntity match = byId(player1, player2, result, date, league, courtType);

        if (match == null) {
            MatchEntity newMatch = new MatchEntity();

            newMatch.setPlayer1(player1);
            newMatch.setPlayer2(player2);
            newMatch.setLeague(league);
            newMatch.setResult(result);
            newMatch.setDate(date);
            newMatch.setCourtType(courtType);

            save(newMatch);
        }
    }


}
