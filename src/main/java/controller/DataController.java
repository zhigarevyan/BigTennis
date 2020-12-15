package controller;

import entity.SeleniumMatch;
import entity.SeleniumMatchList;
import entity.MatchList;
import entity.StringResult;
import entity.StringUser;
import entity.dbEntity.*;
import org.apache.log4j.Logger;
import service.*;

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
    CourtTypeService courtTypeService = new CourtTypeService();

    public static DataController getInstance() {
        if (instance == null) {
            instance = new DataController();
        }

        return instance;
    }

    private DataController() {
    }

    public void insertMatch(String player1, String player2, StringResult result, String date, String league, String courtType) {
        PlayerEntity player1Entity = playerService.getOrNewByName(player1);
        PlayerEntity player2Entity = playerService.getOrNewByName(player2);
        ResultEntity resultEntity = resultService.getOrNewByParams(result);
        LeagueEntity leaguesEntity = leagueService.byName(league);
        CourtTypeEntity courtTypeEntity = courtTypeService.byName(courtType);

        matchService.insertIgnore(player1Entity, player2Entity, resultEntity, date, leaguesEntity, courtTypeEntity);

    }

    public List<String> getLeagues() {
        List<String> leagueList = new ArrayList<>();

        List<LeagueEntity> leaguesEntityList = leagueService.all();
        for(LeagueEntity leaguesEntity: leaguesEntityList) {
            leagueList.add(leaguesEntity.getName());
        }
        return leagueList;
    }

    public MatchList getPlayerMatches(int quantity, String name, String league, String courtType){
        return matchService.getPlMatches(quantity,name,league, courtType);
    }

    public MatchList get2PlayerMatches(int quantity, String p1name, String p2name, String league, String courtType){
        return matchService.get2PlMatches(quantity, p1name, p2name, league, courtType);
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
        return matchService.getMatches(1,"","").getMatch(0).getDateAndTime();
    }

    public void insertMatches(SeleniumMatchList seleniumMatchList) {


            for (SeleniumMatch seleniumMatch : seleniumMatchList.getMatchList()) {
                try {
                    PlayerEntity player1Entity = playerService.getOrNewByName(seleniumMatch.getPlayer1());
                    PlayerEntity player2Entity = playerService.getOrNewByName(seleniumMatch.getPlayer2());
                    ResultEntity resultEntity = resultService.getOrNewByParams(seleniumMatch.getResult());
                    LeagueEntity leaguesEntity = leagueService.getOrNewByName(seleniumMatch.getLeague());
                    CourtTypeEntity courtTypeEntity = courtTypeService.getOrNewByName(seleniumMatch.getCourtType());
                    matchService.insertIgnore(player1Entity, player2Entity, resultEntity, seleniumMatch.getDate(), leaguesEntity, courtTypeEntity);

                } catch (PersistenceException e) {
                    logger.error("Неверные данные пришли в insertMatch", e);
                }
            }
    }






}
