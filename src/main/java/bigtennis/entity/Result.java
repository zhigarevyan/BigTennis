package bigtennis.entity;

import bigtennis.entity.dbEntity.ResultEntity;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Set;

public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    private int p1Score;
    private int p2Score;

    private int p1Fora = 0;
    private int p2Fora = 0;

    private int p1Total = 0;
    private int p2Total = 0;

    private List<Integer> p1SetList = new ArrayList<>();
    private List<Integer> p2SetList = new ArrayList<>();

    private int total = 0;

    private final int P1_WINNER_VALUE = 1;
    private final int P2_WINNER_VALUE = 2;


    private int[] splitString(String score) {
        int[] result = new int[2];

        String[] splitedString = score.split(":");
        result[0] = Integer.parseInt(splitedString[0]);
        result[1] = Integer.parseInt(splitedString[1]);

        return result;
    }

    public String score() {
        return p1Score + ":" + p2Score;
    }

    public int winner() {
        if (p1Score > p2Score) {
            return P1_WINNER_VALUE;
        }
        return P2_WINNER_VALUE;
    }

    public void reverse() {
        int tempNum = p1Score;
        p1Score = p2Score;
        p2Score = tempNum;

        tempNum = p1Total;
        p1Total = p2Total;
        p2Total = tempNum;

        tempNum = p1Fora;
        p1Fora = p2Fora;
        p2Fora = tempNum;

        List<Integer> tempArrayList;

        tempArrayList = p1SetList;
        p1SetList = p2SetList;
        p2SetList = p1SetList;
    }

    //region GSC

    public Result(ResultEntity result) {
        setScores(splitString(result.getScore()));
        setTotals(result.setsToArrString());
        setForas();
    }

    private void setScores(int[] score) {
        p1Score = score[0];
        p2Score = score[1];
    }

    private void setForas() {
        p1Fora = p1Total - p2Total;
        p2Fora = p2Total - p1Total;
    }

    private void setTotals(String[] sets) {
        for (String set : sets) {

            if (set.equals("null")) {
                continue;
            }

            int[] splitedSet = splitString(set);

            int p1SetScore = splitedSet[0];

            p1Total += p1SetScore;
            p1SetList.add(p1SetScore);

            int p2SetScore = splitedSet[1];

            p2Total += p2SetScore;
            p2SetList.add(p2SetScore);

            total = p1Total + p2Total;
        }
    }

    public int getP1Score() {
        return p1Score;
    }

    public int getP2Score() {
        return p2Score;
    }

    public int getP1Fora() {
        return p1Fora;
    }

    public int getP2Fora() {
        return p2Fora;
    }

    public int getP1Total() {
        return p1Total;
    }

    public int getP2Total() {
        return p2Total;
    }

    public int getTotal() {
        return total;
    }

    public List<Integer> getP1SetList() {
        return p1SetList;
    }

    public void setP1SetList(List<Integer> p1SetList) {
        this.p1SetList = p1SetList;
    }

    public List<Integer> getP2SetList() {
        return p2SetList;
    }

    public void setP2SetList(List<Integer> p2SetList) {
        this.p2SetList = p2SetList;
    }

    //endregion
}
