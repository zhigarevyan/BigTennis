package server.entity;

import server.entity.impl.TransferBoolean;
import server.entity.impl.TransferString;
import server.entity.impl.TransferStringList;

import java.io.Serializable;

public interface TransferInterface extends Serializable {

    default tabletennis.entity.MatchList getAsTableTennisMatchList() {
        return (tabletennis.entity.MatchList) this;
    }

    default bigtennis.entity.MatchList getAsBigTennisMatchList() {
        return (bigtennis.entity.MatchList) this;
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
