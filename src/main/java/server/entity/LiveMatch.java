package server.entity;

import tabletennis.entity.MatchList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LiveMatch implements TransferInterface {

    private static final long serialVersionUID = 1L;

    private String player1;
    private String player2;

    private String league;

    private String p1Score;
    private String p2Score;

    private List<String> p1Sets;
    private List<String> p2Sets;

    private Offer offer;

    private static final int SCORE_ELEMENT_INDEX = 0;

    public void setScores(String[] scores) {
        List<String> p1Results = parseScoreLine(scores[0]);
        List<String> p2Results = parseScoreLine(scores[1]);

        p1Score = p1Results.remove(SCORE_ELEMENT_INDEX);
        p1Sets = p1Results;

        p2Score = p2Results.remove(SCORE_ELEMENT_INDEX);
        p2Sets = p2Results;
    }

    private List<String> parseScoreLine(String score) {
        final String SCORE_ELEMENTS_SPLIT_SYMBOL = "\n";

        String[] scoreArray = score.split(SCORE_ELEMENTS_SPLIT_SYMBOL);

        return new ArrayList<>(Arrays.asList(scoreArray));
    }

    public List<String> getP1Sets() {
        return p1Sets;
    }

    public void setP1Sets(List<String> p1Sets) {
        this.p1Sets = p1Sets;
    }

    public List<String> getP2Sets() {
        return p2Sets;
    }

    public void setP2Sets(List<String> p2Sets) {
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
                ", offer=" + offer +
                '}';
    }
}
