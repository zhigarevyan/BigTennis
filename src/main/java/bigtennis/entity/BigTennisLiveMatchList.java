package bigtennis.entity;

import server.entity.LiveMatch;
import server.entity.LiveMatchList;

import java.util.List;

public class BigTennisLiveMatchList extends LiveMatchList {

    private MatchList playersMatchList;
    private MatchList p1MatchList;
    private MatchList p2MatchList;

    public BigTennisLiveMatchList(List<LiveMatch> liveMatchList) {
        super(liveMatchList);
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

}
