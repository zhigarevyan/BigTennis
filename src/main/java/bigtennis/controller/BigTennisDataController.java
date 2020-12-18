package bigtennis.controller;

import bigtennis.entity.User;
import bigtennis.entity.selenium.SeleniumMatch;
import bigtennis.entity.selenium.SeleniumMatchList;
import bigtennis.entity.MatchList;
import bigtennis.entity.selenium.StringResult;
import bigtennis.entity.dbEntity.*;
import org.apache.log4j.Logger;
import bigtennis.service.*;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class BigTennisDataController {

    private static final Logger logger = org.apache.log4j.LogManager.getLogger(BigTennisDataController.class);

    private static BigTennisDataController instance;
    MatchService matchService = new MatchService();
    PlayerService playerService = new PlayerService();
    ResultService resultService = new ResultService();
    LeagueService leagueService = new LeagueService();
    UserService userService = new UserService();
    UserRoleService userRoleService = new UserRoleService();
    CourtTypeService courtTypeService = new CourtTypeService();

    public static BigTennisDataController getInstance() {
        if (instance == null) {
            instance = new BigTennisDataController();
        }

        return instance;
    }

    private BigTennisDataController() {
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

    public List<String> getCourts() {
        List<String> courtList = new ArrayList<>();

        List<CourtTypeEntity> courtTypeEntityList = courtTypeService.all();
        for(CourtTypeEntity courtTypeEntity: courtTypeEntityList) {
            courtList.add(courtTypeEntity.getName());
        }
        return courtList;
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

    public void signUp(String nickname, String key) {
        final int UNREG_USER_ID = 4;
        UserEntity userEntity = new UserEntity();
        userEntity.setName(nickname);
        userEntity.setKey(key);
        UserRoleEntity unregUserRoleEntity = userRoleService.getEntitybyId(UNREG_USER_ID);
        userEntity.setUserRolesByRole(unregUserRoleEntity);

        userService.save(userEntity);
    }

    public User getUser(String key) {
        return userService.byKey(key);
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
