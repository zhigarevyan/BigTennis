package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Delimiter implements Serializable {

    List<MatchList> matchListByDate;
    List<MatchList> matchListByQuantity;

    private int delimQuantity;

    private void initMatchListByQuantity(MatchList matchList) {
        int matchesLeftQuantity = matchList.size();
        int offsetIndex = 0;
        int endIndex;
        do {
            endIndex = delimQuantity - 1 + offsetIndex;

            matchListByQuantity.add(new MatchList(matchList, offsetIndex, endIndex));

            offsetIndex += delimQuantity;
            matchesLeftQuantity -= delimQuantity;
        } while (matchesLeftQuantity > 0);
    }

    private void initMatchListByDate(MatchList matchList) {
        String prevDate = matchList.getList().get(0).getDate();
        matchListByDate.add(new MatchList(matchList, prevDate));

        for (Match match : matchList.getList()) {
            if (!match.getDate().equals(prevDate)) {
                matchListByDate.add(new MatchList(matchList, match.getDate()));

                prevDate = match.getDate();
            }
        }
    }
    //region GSC

    public Delimiter(MatchList matchList) {
        delimQuantity = 10;
        initLists();

        initMatchListByQuantity(matchList);
        initMatchListByDate(matchList);
    }

    public Delimiter(MatchList matchList, int delimQuantity) {
        this.delimQuantity = delimQuantity;
        initLists();

        initMatchListByQuantity(matchList);
        initMatchListByDate(matchList);
    }

    private void initLists() {
        matchListByDate = new ArrayList<>();
        matchListByQuantity = new ArrayList<>();
    }

    public List<MatchList> getMatchListByDate() {
        return matchListByDate;
    }

    public List<MatchList> getMatchListByQuantity() {
        return matchListByQuantity;
    }

    //endregion
}
