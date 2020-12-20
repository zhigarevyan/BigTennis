package server.entity;

import tabletennis.entity.MatchList;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiveMatch implements TransferInterface {

    private static final long serialVersionUID = 1L;

    private String player1;
    private String player2;

    private String league;

    private String p1Score;
    private String p2Score;

    private String p1Sets;
    private String p2Sets;

    private MatchList playersMatchList;
    private MatchList p1MatchList;
    private MatchList p2MatchList;

    private Offer offer;

    public void setScores(String[] scores) {
        String[] p1Results = parseScoreLine(scores[0]);
        String[] p2Results = parseScoreLine(scores[1]);

        p1Score = p1Results[0];
        p1Sets = p1Results[1];

        p2Score = p2Results[0];
        p2Sets = p2Results[1];
    }

    private String[] parseScoreLine(String score) {
        final String DIGIT_REGEXP = "(\\d+)";
        String[] result = new String[2];

        Pattern pattern = Pattern.compile(DIGIT_REGEXP);

        Matcher matcher = pattern.matcher(score);
        StringBuilder setsString = new StringBuilder();

        matcher.find();
        result[0] = matcher.group();

        while (matcher.find()) {
            setsString.append(matcher.group()).append(" ");
        }
        result[1] = setsString.toString();
        return result;
    }

    public String getP1Sets() {
        return p1Sets;
    }

    public void setP1Sets(String p1Sets) {
        this.p1Sets = p1Sets;
    }

    public String getP2Sets() {
        return p2Sets;
    }

    public void setP2Sets(String p2Sets) {
        this.p2Sets = p2Sets;
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

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getP1Score() {
        return p1Score;
    }

    public void setP1Score(String p1Score) {
        this.p1Score = p1Score;
    }

    public String getP2Score() {
        return p2Score;
    }

    public void setP2Score(String p2Score) {
        this.p2Score = p2Score;
    }

    public MatchList getPlayersMatchList() {
        return playersMatchList;
    }

    public void setPlayersMatchList(MatchList playersMatchList) {
        this.playersMatchList = playersMatchList;
    }

    public MatchList getP1MatchList() {
        return p1MatchList;
    }

    public void setP1MatchList(MatchList p1MatchList) {
        this.p1MatchList = p1MatchList;
    }

    public MatchList getP2MatchList() {
        return p2MatchList;
    }

    public void setP2MatchList(MatchList p2MatchList) {
        this.p2MatchList = p2MatchList;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "LiveMatch{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", league='" + league + '\'' +
                ", p1Score='" + p1Score + '\'' +
                ", p2Score='" + p2Score + '\'' +
                ", p1Sets='" + p1Sets + '\'' +
                ", p2Sets='" + p2Sets + '\'' +
                ", playersMatchList=" + playersMatchList +
                ", p1MatchList=" + p1MatchList +
                ", p2MatchList=" + p2MatchList +
                ", offer=" + offer +
                '}';
    }
}
