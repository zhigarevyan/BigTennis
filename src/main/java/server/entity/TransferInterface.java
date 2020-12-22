package server.entity;

import bigtennis.entity.User;
import server.entity.impl.TransferBoolean;
import server.entity.impl.TransferString;
import server.entity.impl.TransferStringList;

import java.io.Serializable;

public interface TransferInterface extends Serializable {

    default LiveMatchList getAsTableTennisLiveMatch() {
        return (LiveMatchList) this;
    }

    default tabletennis.entity.MatchList getAsTableTennisMatchList() {
        return (tabletennis.entity.MatchList) this;
    }

    default bigtennis.entity.MatchList getAsBigTennisMatchList() {
        return (bigtennis.entity.MatchList) this;
    }

    default User getAsUser() {
        return (User) this;
    }

    default TransferBoolean getAsAccess() {
        return (TransferBoolean) this;
    }

    default TransferString getAsTransferString() {
        return (TransferString) this;
    }

    default TransferStringList getAsTransferStringList() {
        return (TransferStringList) this;
    }

}
