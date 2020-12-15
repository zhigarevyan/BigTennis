package entity;

import entity.dbEntity.MatchEntity;
import util.DateFormater;

import java.io.Serializable;

public class Match implements Serializable {

    private String date;
    private String player1;
    private String player2;
    private Result result;
    private String league;
    private String courtType;

    public void reverse() {
        result.reverse();
        reversePlayers();
    }

    private void reversePlayers() {
        String tempString = player1;
        player1 = player2;
        player2 = tempString;
    }

    public String toString() {
        return String.format("%s| %s| %d| %d| %d| %d| %d| %s\n", dateByApp(), result.score(), result.getP1Fora(), result.getP1Total(),
                result.getP2Fora(), result.getP2Total(), result.getTotal(), courtType);
    }

    //region GSC

    public Match(MatchEntity match) {
        this.date = match.getDate();
        this.player1 = match.getPlayer1().getName();
        this.player2 = match.getPlayer2().getName();
        this.result = new Result(match.getResult());
        this.league = match.getLeague().getName();
    }

    public String getDate() {
        return DateFormater.getDate(this.date);
    }

    public String getDateAndTime() {
        return date;
    }

    public String dateByApp() {
        return DateFormater.dateByApp(this.date);
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public Result getResult() {
        return result;
    }

    public String getLeague() {
        return league;
    }

    public String getCourtType() {
        return courtType;
    }

    public void setCourtType(String courtType) {
        this.courtType = courtType;
    }

    //endregion
}
