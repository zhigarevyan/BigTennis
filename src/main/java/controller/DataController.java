package controller;

import bigtennis.entity.SeleniumMatch;
import bigtennis.entity.SeleniumMatchList;
import bigtennis.entity.MatchList;
import bigtennis.entity.StringResult;
import bigtennis.entity.StringUser;
import bigtennis.entity.dbEntity.LeagueEntity;
import bigtennis.entity.dbEntity.PlayerEntity;
import bigtennis.entity.dbEntity.ResultEntity;
import bigtennis.entity.dbEntity.UserEntity;
import org.apache.log4j.Logger;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class DataController {

    private static final Logger logger = org.apache.log4j.LogManager.getLogger(DataController.class);

    private static DataController instance;
    MatchService matchService = new MatchService();
    PlayerService playerService = new PlayerService();
    ResultService resultService = new ResultService();
    LeagueService leagueService = new LeagueService();
    UserService userService = new UserService();


    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }

        return instance;
    }

    private DataController() {
    }

    public void insertMatch(String player1, String player2, StringResult result, String date, String league) {
        PlayerEntity player1Entity = playerService.getOrNewByName(player1);
        PlayerEntity player2Entity = playerService.getOrNewByName(player2);
        ResultEntity resultEntity = resultService.getOrNewByParams(result);
        LeagueEntity leaguesEntity = leagueService.byName(league);

        matchService.insertIgnore(player1Entity, player2Entity, resultEntity, date, leaguesEntity);

    }

    public List<String> getLeagues() {
        List<String> leagueList = new ArrayList<>();

        List<LeagueEntity> leaguesEntityList = leagueService.all();
        for(LeagueEntity leaguesEntity: leaguesEntityList) {
            leagueList.add(leaguesEntity.getName());
        }
        return leagueList;
    }

    public MatchList getPlayerMatches(int quantity, String name, String league){
        return matchService.getPlMatches(quantity,name,league);
    }

    public MatchList get2PlayerMatches(int quantity, String p1name, String p2name, String league){
        return matchService.get2PlMatches(quantity, p1name, p2name, league);
    }

    public List<String> getAllPlayerNames(){
        return playerService.all();
    }

    public List<String> getUsers(){
        List<String> userList = new ArrayList<>();

        List<UserEntity> appUserList = userService.all();
        for(UserEntity userEntity: appUserList) {
            userList.add(userEntity.getName());
        }
        return userList;
    }

    public boolean checkUserAccess(String mac){
        return userService.checkAccess(mac);
    }

    public String getLastMatchDate(){
        return matchService.getMatches(1,"").getMatch(0).getDateAndTime();
    }


    public void insertMatches(SeleniumMatchList seleniumMatchList) {

        try {

            for (SeleniumMatch seleniumMatch : seleniumMatchList.getMatchList()) {
                PlayerEntity player1Entity = playerService.getOrNewByName(seleniumMatch.getPlayer1());
                PlayerEntity player2Entity = playerService.getOrNewByName(seleniumMatch.getPlayer2());
                ResultEntity resultEntity = resultService.getOrNewByParams(seleniumMatch.getResult());
                LeagueEntity leaguesEntity = leagueService.byName(seleniumMatch.getLeague());

                matchService.insertIgnore(player1Entity, player2Entity, resultEntity, seleniumMatch.getDate(), leaguesEntity);
            }
        } catch (PersistenceException e) {
            logger.error("Неверные данные пришли в insertMatch", e);
        }
    }






}
