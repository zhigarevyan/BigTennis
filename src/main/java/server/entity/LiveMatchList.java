package server.entity;

import server.entity.impl.TransferElement;

import java.util.List;

public class LiveMatchList extends TransferElement {

    private List<LiveMatch> liveMatchList;

    @Override
    public String toString() {
        return "LiveMatchList{" +
                "liveMatchList=" + liveMatchList +
                '}';
    }

    public LiveMatchList(List<LiveMatch> liveMatchList) {
        this.liveMatchList = liveMatchList;
    }

    public List<LiveMatch> getList() {
        return liveMatchList;
    }

    public void setLiveMatchList(List<LiveMatch> liveMatchList) {
        this.liveMatchList = liveMatchList;
    }
}
