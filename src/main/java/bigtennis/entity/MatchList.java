package bigtennis.entity;

import controller.connection.entity.impl.TransferElement;
import bigtennis.entity.dbEntity.MatchEntity;


import java.util.ArrayList;
import java.util.List;

    public class MatchList extends TransferElement {

    private ArrayList<Match> matchList;

    public void sortByP1(String p1name) {
        for (Match match : matchList) {
            if (!(match.getPlayer1().equals(p1name))) {
                match.reverse();
            }
        }
    }

    public int size() {
        return matchList.size();
    }

    public int playerWins(int playerNum) {
        int playerWinsQuantity = 0;
        for (Match match : matchList) {
            if (match.getResult().winner() == playerNum) {
                playerWinsQuantity++;
            }
        }
        return playerWinsQuantity;
    }

    //region GSC

    public Match getMatch(int index) {
        return matchList.get(index);
    }

    public List<Match> getList() {
        return matchList;
    }

    public MatchList(List<MatchEntity> listMatchEntity) {
        matchList = new ArrayList<>();
        for (MatchEntity matchEntity : listMatchEntity) {
            matchList.add(new Match(matchEntity));
        }
    }

    public MatchList(MatchList matchList, int startIndex, int endIndex) {
        this.matchList = new ArrayList<>();
        if (endIndex > matchList.size()) {
            endIndex = matchList.size() - 1;
        }
        for (int index = startIndex; index <= endIndex; index++) {
            this.matchList.add(matchList.getMatch(index));
        }
    }

    public MatchList(MatchList matchList, String date) {
        this.matchList = new ArrayList<>();
        for (Match matchEntity : matchList.getList()) {
            if (matchEntity.getDate().equals(date)) {
                this.matchList.add(matchEntity);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder resultSB = new StringBuilder();
        if (size() != 0) {
            Match firstMatch = getMatch(0);

            String line = String.format("%s - %d | %d - %s\n", firstMatch.getPlayer1(), playerWins(1), playerWins(2), firstMatch.getPlayer2());
            resultSB.append(line);
            for (Match match : matchList) {
                line = match.toString();
                resultSB.append(line);
            }
        }
        return resultSB.toString();
    }
    //endregion
}
