package bigtennis.entity.selenium;

public class SeleniumMatch {

    private String player1;
    private String player2;
    private StringResult result;
    private String date;
    private String league;
    private String courtType;

    //region GSC

    public SeleniumMatch(){}

    public SeleniumMatch(String player1, String player2, StringResult result, String date, String league) {
        this.player1 = player1;
        this.player2 = player2;
        this.result = result;
        this.date = date;
        this.league = league;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public StringResult getResult() {
        return result;
    }

    public void setResult(StringResult result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getCourtType() {
        return courtType;
    }

    public void setCourtType(String courtType) {
        this.courtType = courtType == null ? "-" : courtType;
    }

    @Override
    public String toString() {
        return "SeleniumMatch{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", result=" + result +
                ", date='" + date + '\'' +
                ", league='" + league + '\'' +
                ", courtType='" + courtType + '\'' +
                '}';
    }

    //endregion
}
