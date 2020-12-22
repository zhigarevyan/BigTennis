package tabletennis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Delimiter implements Serializable {

    private static final long serialVersionUID = 1L;

    List<MatchList> matchListByDate;
    List<MatchList> matchListByQuantity;

    private int delimQuantity;

    private void initMatchListByQuantity(MatchList matchList) {
        int matchesLeft = matchList.size();

        int steps = matchesLeft / delimQuantity;
        int offsetIndex;
        int endIndex;

        for (int index = 0; index < steps; index++) {
            offsetIndex = delimQuantity * index;
            endIndex = offsetIndex + delimQuantity - 1;
            matchListByQuantity.add(new MatchList(matchList, offsetIndex, endIndex));
        }
        int left = matchesLeft % delimQuantity; //9

        if(left>0) {
            offsetIndex = delimQuantity * steps;
            endIndex = offsetIndex+left-1;
            matchListByQuantity.add(new MatchList(matchList, offsetIndex, endIndex));
        }
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
